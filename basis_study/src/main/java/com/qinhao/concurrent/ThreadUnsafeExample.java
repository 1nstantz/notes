package com.qinhao.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/1/8 - 14:34
 */
public class ThreadUnsafeExample {
    int count;


    public void add(){
        count++;
    }

    public int get() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 1000;
        ThreadUnsafeExample example = new ThreadUnsafeExample();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executor.execute(()->{
                example.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executor.shutdown();
        System.out.println(example.get());
    }
}
