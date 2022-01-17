package com.qinhao.springboot.service;

import com.qinhao.springboot.pojo.User;

import java.util.List;

/**
 * @author qinhao
 * @date 2022/1/11 - 10:00
 */
public interface UserService {
    int addOneUser(User user);

    int deleteById(Long id);

    Integer updateOneUser(User user);

    User getById(Long Id);

    List<User> getAllTeacher();
}
