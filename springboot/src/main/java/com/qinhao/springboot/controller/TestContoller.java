package com.qinhao.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/3/12 - 11:44
 */
@RestController
@Slf4j
public class TestContoller {
    @RequestMapping("/")
    public String getRequest() {
        log.info("=============>controller被访问了");
        return "hello world";
    }
}
