package com.qinhao.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinhao
 * @date 2022/7/27 - 18:06
 */
@RestController
public class RedisContrller {


    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/buy")
    public String buy() {
        String num = redisTemplate.opsForValue().get("good");
        int goodnum = Integer.parseInt(num);
        if (goodnum < 1) {
            return "库存不足";
        }
        goodnum--;
        redisTemplate.opsForValue().set("good", String.valueOf(goodnum));
        return "购买成功";
    }
}
