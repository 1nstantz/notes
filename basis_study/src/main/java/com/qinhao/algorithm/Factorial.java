package com.qinhao.algorithm;

import org.junit.Test;

/**
 * @author qinhao
 * @date 2022/4/27 - 11:02
 */
public class Factorial {
    @Test
    public void sum() {
        int num = 10;
        int result = recursion(num);
        System.out.println(result);
    }

    private int recursion(int num) {
        if (num == 1) {
            return 1;
        }
        return num*recursion(num-1);
    }
}
