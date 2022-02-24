package com.qinhao;

import java.util.concurrent.CountDownLatch;

/**
 * @author qinhao
 * @date 2022/2/24 - 9:29
 */
public class UseCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println("线程执行");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("执行完成");
    }
}
