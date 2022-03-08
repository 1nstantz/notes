package com.qinhao.future;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author qinhao
 * @date 2022/3/7 - 11:34
 */
public class UseCompletableFuture {
    public ExecutorService executor = Executors.newFixedThreadPool(4);

    /**
     * 1.8提供的异步函数式编程
     */
    @Test
    public void useCompletableFuture() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }, executor);

        System.out.println(future.get());  //阻塞的获取结果  ''helllo world"
    }


    /**
     * 阻塞式获取结果
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void usefuture() throws ExecutionException, InterruptedException {
        Future future = executor.submit(() -> {
            //do some thing
            Thread.sleep(100);
            return "i am ok";
        });
        // isDone 获取任务是否完成
        System.out.println(future.isDone());
        // get() 方法阻塞式的获取返回结果
        System.out.println(future.get());
    }
}
