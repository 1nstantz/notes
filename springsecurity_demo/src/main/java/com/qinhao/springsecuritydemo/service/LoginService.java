package com.qinhao.springsecuritydemo.service;

import com.qinhao.springsecuritydemo.pojo.Users;
import com.qinhao.springsecuritydemo.utils.Response;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/7/2 - 11:39
 */
public interface LoginService {
    Response login(Users user);
}
