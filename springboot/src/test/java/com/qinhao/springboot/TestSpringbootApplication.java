package com.qinhao.springboot;

import com.qinhao.springboot.service.CalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author qinhao
 * @date 2022/7/26 - 16:44
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSpringbootApplication {
    @Autowired
    CalService calService;

    @Test
    public void calTest() {
        System.out.println("spring version：" + SpringVersion.getVersion() + "springbootVersion" + SpringBootVersion.getVersion());
        System.out.println("========================");
        int cal = calService.cal(2,2);
        System.out.println("==============================计算结果"+cal);
    }
}
