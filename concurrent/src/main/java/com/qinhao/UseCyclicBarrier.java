package com.qinhao;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author qinhao
 * @date 2022/2/24 - 9:51
 */
public class UseCyclicBarrier {
    public static void main(String[] args) {
        //和CountDownLatch相反，也就是做加法，开始是0，加到某个值的时候就执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙");
        });

        for (int i = 0; i < 7; i++) {
            new Thread(()->{
                System.out.println("1颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
