package com.qinhao.test;

/**
 * describ
 *
 * @author qinhao
 * @date 2021/11/23 - 22:24
 */
public class TestComparable {
    public static Comparable getMax(Comparable c1, Comparable c2) {
        return c1.compareTo(c2) >= 0 ? c1 : c2;
    }
}
