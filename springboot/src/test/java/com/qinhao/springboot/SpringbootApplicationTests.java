package com.qinhao.springboot;

import com.qinhao.springboot.mapper.UserMapper;
import com.qinhao.springboot.pojo.User;
import com.qinhao.springboot.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        User zhangsan = new User().setAge(24).setId(103l).setName("qinhao");
        int i = userService.addOneUser(zhangsan);
        Assert.assertEquals("错误",1,i);
    }

}
