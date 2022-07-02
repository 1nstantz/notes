package com.qinhao.springsecuritydemo.service.impl;

import com.qinhao.springsecuritydemo.pojo.Users;
import com.qinhao.springsecuritydemo.service.LoginService;
import com.qinhao.springsecuritydemo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/7/2 - 11:38
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public Response login(Users user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登陆失败");
        }
        return null;
    }
}
