package com.qinhao.concurrent.test;

import lombok.extern.slf4j.Slf4j;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/1/8 - 14:59
 */
@Slf4j(topic = "c.Tet2")
public class Test2 {
    public static void main(String[] args) {
        Runnable child = () -> log.debug("child");
        new Thread(child, "child").start();
        log.debug("parent");
    }
}
