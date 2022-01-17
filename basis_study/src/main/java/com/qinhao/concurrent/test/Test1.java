package com.qinhao.concurrent.test;

import lombok.extern.slf4j.Slf4j;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/1/8 - 14:53
 */
@Slf4j(topic = "c.Tet1")
public class Test1 {
    public static void main(String[] args) {
        new Thread(() -> log.debug("新的线程")).start();

        log.debug("主线程");
    }
}
