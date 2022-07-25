package com.qinhao.springboot.controller;

import com.qinhao.springboot.common.R;
import com.qinhao.springboot.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/3/12 - 11:44
 */
@RestController
@Slf4j
@CrossOrigin
public class TestContoller {
    @RequestMapping("/")
    public R getRequest() {
        log.info("=============>controller被访问了");
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1L,"qinhao",18));
        list.add(new User(2L,"zsp",19));
        return R.ok().data("rows", list);
    }
}
