package com.qinhao;

import java.util.concurrent.*;

/**
 * @author qinhao
 * @date 2022/2/24 - 10:05
 */
public class UseThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                4,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                r -> new Thread(),//线程工厂
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
