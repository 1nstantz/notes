package com.qinhao.springboot.cicledi;

import org.springframework.stereotype.Component;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/2/26 - 17:45
 */
@Component
public class B {
    private A a;
    public B(){
        System.out.println("BBBBBBBBBBBBBBBBBBBBB");
    }
}
