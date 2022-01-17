package com.qinhao.concurrent.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/1/8 - 15:05
 */
@Slf4j(topic = "c.t3")
public class Test3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            log.debug("running");
            Thread.sleep(1000);
            return 1;
        });

        new Thread(task, "child").start();
        Object o = task.get();
        System.out.println(o.toString());
        log.debug("main");

    }
}
