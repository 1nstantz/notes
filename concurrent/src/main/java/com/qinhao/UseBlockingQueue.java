package com.qinhao;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author qinhao
 * @date 2022/2/24 - 10:21
 */
public class UseBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> strs = new ArrayBlockingQueue<>(2);
        //执行add方法，向已经满的ArrayBlockingQueue中添加元素时候，会抛出异常
        strs.add("a");
        //空队列remove抛异常
        strs.remove();


        //如果阻塞队列满了后，会返回false，否者返回true
        strs.offer("b");
        //同时在取的时候，如果队列已空，那么会返回null
        strs.poll();
        //offer  poll 加时间
        strs.offer("b",2L, TimeUnit.SECONDS) ;
        strs.poll(2L, TimeUnit.SECONDS);

        //满了就阻塞住
        strs.put("c");
        //取的时候没数据就阻塞住
        strs.take();
    }
}
