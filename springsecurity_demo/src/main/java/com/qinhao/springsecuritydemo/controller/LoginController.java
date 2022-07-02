package com.qinhao.springsecuritydemo.controller;

import com.qinhao.springsecuritydemo.pojo.Users;
import com.qinhao.springsecuritydemo.service.LoginService;
import com.qinhao.springsecuritydemo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/7/2 - 11:35
 */
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/user/login")
    public Response login(@RequestBody Users user) {
        return loginService.login(user);
    }
}
