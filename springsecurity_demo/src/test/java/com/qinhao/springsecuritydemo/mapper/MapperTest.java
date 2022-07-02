package com.qinhao.springsecuritydemo.mapper;

import com.qinhao.springsecuritydemo.mapper.UserMapper;
import com.qinhao.springsecuritydemo.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/7/2 - 10:50
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
        List<Users> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
