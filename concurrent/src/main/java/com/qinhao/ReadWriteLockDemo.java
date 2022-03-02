package com.qinhao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    /**
     * 独占锁(写锁)一次只能有一个线程占有
     * 共享锁(读锁)一次可以有多个线程占有
     * ReadWriteLock
     * 读-读  可以共存
     * 写-读  不能共存
     * 写-写  不能共存
     * */
    public static void main(String[] args) {
        MyCacheLock myCache=new MyCacheLock();
//        存
        for (int i = 1; i <= 5; i++) {
            final int temp=i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }
//        取
        for (int i = 1; i <= 5; i++) {
            final int temp=i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}

class MyCacheLock{
    private volatile Map<String,Object> map=new HashMap<>();
//    读写锁：更加细粒度的操作
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    //存，写入的时候，只希望同时只有一个线程写
    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    //取，读的时候所有人都可以读
    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
class MyCache{
    private volatile Map<String,Object> map=new HashMap<>();
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入OK");
    }
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        Object value = map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取OK");
    }
}