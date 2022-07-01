package com.qinhao.redisson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author qinhao
 * @date 2022/5/12 - 14:06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestClient {
    @Autowired
    RedissonClient redissonClient;

    @Test
    public void TestRedisson() {
        System.out.println(redissonClient);
    }
}
