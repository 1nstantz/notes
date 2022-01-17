package com.qinhao.springboot.service.impl;

import com.qinhao.springboot.pojo.User;
import com.qinhao.springboot.mapper.UserMapper;
import com.qinhao.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qinhao
 * @date 2022/1/11 - 10:03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addOneUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public Integer updateOneUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public User getById(Long Id) {
        return userMapper.selectById(Id);
    }

    @Override
    public List<User> getAllTeacher() {
        return userMapper.selectList(null);
    }
}
