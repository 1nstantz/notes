package com.qinhao.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper , User> implements UserService {

}
