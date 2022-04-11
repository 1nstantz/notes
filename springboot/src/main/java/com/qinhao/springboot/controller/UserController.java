package com.qinhao.springboot.controller;

import com.qinhao.springboot.pojo.User;
import com.qinhao.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinhao
 * @date 2022/3/25 - 15:35
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/")
    public void insert(@RequestBody User user) {
        userService.save(user);
        System.out.println("成功");
    }
}
