package com.qinhao.redisson.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author qinhao
 * @date 2022/5/12 - 14:13
 */
@RestController
@RequestMapping("/")
public class TestController {

    @Resource
    private RedissonClient redisson;

    @Autowired
    private RedisTemplate redisTemplate;


    @PostConstruct
    public void initReidis() {
        System.out.println("设置库存为10");
        redisTemplate.opsForValue().set("store",10);
    }


    @ResponseBody
    @GetMapping("/testlock")
    public String testLock() {
        System.out.println("库存为：" + redisTemplate.opsForValue().get("store"));
        // 1.获取锁，只要锁的名字一样，获取到的锁就是同一把锁。
        RLock lock = redisson.getLock("lock");

        // 2.加锁
        lock.lock();
        try {
            System.out.println("加锁成功，执行库存-1。线程 ID：" + Thread.currentThread().getId());
            redisTemplate.opsForValue().decrement("store",1);
            System.out.println("库存为：" + redisTemplate.opsForValue().get("store"));
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            // 3.解锁
            System.out.println("Finally，释放锁成功。线程 ID：" + Thread.currentThread().getId());
        }

        return "test lock ok";
    }
}
