package com.qinhao.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/6/29 - 22:04
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("hello")
    public String add() {
        return "hello security";
    }
}
