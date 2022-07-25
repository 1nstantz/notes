package com.qinhao.springboot;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author qinhao
 * @date 2022/7/25 - 13:58
 */
public class ConditionTest {
    int num = 1;
    private Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();


    public void print1() {

        lock.lock();
        try {
            while (num != 1) {
                condition1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + num);
            }
            num = 2;
            condition2.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void print2() {

        lock.lock();
        try {
            while (num != 2) {
                condition2.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + num);
            }
            num = 3;
            condition3.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


    public void print3() {

        lock.lock();
        try {
            while (num != 3) {
                condition3.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + num);
            }
            num = 1;
            condition1.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        ConditionTest conditionTest = new ConditionTest();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                conditionTest.print1();
            }
        }, "线程1").start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                conditionTest.print2();
            }
        }, "线程2").start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                conditionTest.print3();
            }
        }, "线程3").start();



    }

}
