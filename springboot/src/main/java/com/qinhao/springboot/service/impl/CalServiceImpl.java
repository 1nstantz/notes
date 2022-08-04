package com.qinhao.springboot.service.impl;

import com.qinhao.springboot.service.CalService;
import org.springframework.stereotype.Service;

/**
 * @author qinhao
 * @date 2022/7/26 - 16:36
 */
@Service
public class CalServiceImpl implements CalService {


    @Override
    public int cal(int x, int y) {
        int i = x + y;
        System.out.println("=====我被调用了=====，结果是："+i);
        return i;
    }
}
