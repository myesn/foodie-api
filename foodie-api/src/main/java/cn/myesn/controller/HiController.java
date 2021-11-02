package cn.myesn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// 在 swagger 中隐藏
@ApiIgnore
@RestController
public class HiController {

    @GetMapping("hi")
    public ResponseEntity<String> hi(){
        return ResponseEntity.ok("hi");
    }

    @GetMapping("set-session")
    public ResponseEntity<?> setSession(HttpServletRequest request) {
        final HttpSession session =  request.getSession();
        final String sessionName = "user";
        session.setAttribute(sessionName,"myesn");
        session.setMaxInactiveInterval(3600);
        final Object sessionValue = session.getAttribute(sessionName);
//        session.removeAttribute(sessionName);

        return ResponseEntity.ok().build();
    }
}
