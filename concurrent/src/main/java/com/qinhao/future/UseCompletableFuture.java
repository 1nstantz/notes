package com.qinhao.future;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * runAsync没有返回结果
 * supplyAsync 可以获取返回结果，get()阻塞获取返回结果
 * AcceptAsync没有返回结果，但是可以传参。
 *
 *  这三个方法前面加then实现链式编程
 * @author qinhao
 * @date 2022/3/7 - 11:34
 */
public class UseCompletableFuture {
    public ExecutorService executor = Executors.newFixedThreadPool(4);

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
        }, executor).whenCompleteAsync((res,exception)->{//whenCompleteAsync可以捕获到异常信息
            System.out.println(res);
            System.out.println(exception);
        },executor).exceptionally((e)->{//exceptionally对异常做处理
            System.out.println(e);
            return "失败";
        });


        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }, executor).handleAsync((res, ex) -> {
            System.out.println(res);
            return "shibai";
        }, executor);
        //handlerAsync就是whenCompleteAsync和exceptionally的结合
        System.out.println(future.get());  //阻塞的获取结果  ''helllo world"
    }
}
