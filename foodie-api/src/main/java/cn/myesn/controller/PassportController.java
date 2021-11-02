package cn.myesn.controller;

import cn.myesn.exception.ServiceException;
import cn.myesn.pojo.Users;
import cn.myesn.pojo.bo.UserBO;
import cn.myesn.service.UserService;
import cn.myesn.utils.CookieUtils;
import cn.myesn.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

@Api(value = "登录注册", tags = {"用于登录注册的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {

    private final UserService userService;

    public PassportController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "检查用户名是否已存在", notes = "检查用户名是否已存在", httpMethod = "GET")
    @GetMapping("check-username")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        if (StringUtils.isBlank(username)) {
            throw new ServiceException("用户名不能为空");
        }

        if (userService.existsUserName(username)) {
            throw new ServiceException("用户名已存在");
        }

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody UserBO userBO) {
        validateSignup(userBO);
        userService.create(userBO);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("signin")
    public ResponseEntity<Users> signin(@RequestBody UserBO userBO,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws NoSuchAlgorithmException {
        validateSignin(userBO);
        final Users user = userService.login(userBO.getUsername(), userBO.getPassword());

        protectPrivacy(user);
        CookieUtils.setCookie(request, response,
                "foodie-user",
                JsonUtils.objectToJson(user),
                true);

        return ResponseEntity.ok(user);
    }


    private void protectPrivacy(Users user) {
        user.setPassword(null);
        user.setMobile(null);
        user.setEmail(null);
        user.setCreatedTime(null);
        user.setUpdatedTime(null);
        user.setBirthday(null);
    }

    private void validateSignin(UserBO userBO) {
        if (ObjectUtils.isEmpty(userBO)) {
            throw new ServiceException("参数不能为空");
        }

        if (StringUtils.isBlank(userBO.getUsername()) || StringUtils.isBlank(userBO.getPassword())) {
            throw new ServiceException("用户名或密码不能为空");
        }
    }

    private void validateSignup(UserBO userBO) {
        if (ObjectUtils.isEmpty(userBO)) {
            throw new ServiceException("参数不能为空");
        }

        if (StringUtils.isBlank(userBO.getUsername()) ||
                StringUtils.isBlank(userBO.getPassword()) ||
                StringUtils.isBlank(userBO.getConfirmPassword())) {
            throw new ServiceException("用户名或密码不能为空");
        }

        if (userService.existsUserName(userBO.getUsername())) {
            throw new ServiceException("用户名已存在");
        }

        if (userBO.getPassword().length() < 6) {
            throw new ServiceException("密码长度不能小于6");
        }

        if (!userBO.getPassword().equals(userBO.getConfirmPassword())) {
            throw new ServiceException("两次密码输入不一致");
        }
    }
}
