package cn.myesn.service.impl;

import cn.myesn.enums.GenderEnum;
import cn.myesn.exception.ServiceException;
import cn.myesn.mapper.UsersMapper;
import cn.myesn.pojo.Users;
import cn.myesn.pojo.bo.UserBO;
import cn.myesn.service.UserService;
import cn.myesn.utils.DateUtil;
import cn.myesn.utils.MD5Utils;
import org.apache.commons.lang3.ObjectUtils;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private final UsersMapper usersMapper;
    private final Sid sid;

    private static final String DEFAULT_USER_FACE = "https://himg.bdimg.com/sys/portrait/item/public.1.afd8b253.V-0gILRfGfm56XRA1TkXuw.jpg";

    public UserServiceImpl(UsersMapper usersMapper, Sid sid) {
        this.usersMapper = usersMapper;
        this.sid = sid;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean existsUserName(String username) {
        // 测试 aop 记录方法调用时长
//        try {
//            Thread.sleep(2500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        final Example example = new Example(Users.class);
        final Example.Criteria userCriteria = example.createCriteria();
        userCriteria.andEqualTo("username", username);

        return usersMapper.selectOneByExample(example) != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users create(UserBO userBO) {
        final String userId = sid.nextShort();
        final Users user = new Users();
        user.setId(userId);
        user.setUsername(userBO.getUsername());
        try {
            user.setPassword(MD5Utils.encrypt(userBO.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setNickname(userBO.getUsername());
        user.setFace(DEFAULT_USER_FACE);
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        user.setSex(GenderEnum.secret.value);
        final Date now = new Date();
        user.setCreatedTime(now);
        user.setUpdatedTime(now);

        usersMapper.insert(user);

        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users login(String username, String password) throws NoSuchAlgorithmException {
        final Example userExample = new Example(Users.class);
        final Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", username);
        userCriteria.andEqualTo("password", MD5Utils.encrypt(password));

        final Users user = usersMapper.selectOneByExample(userExample);
        if (ObjectUtils.isEmpty(user)) {
            throw new ServiceException("用户名或密码不正确");
        }

        return user;
    }
}
