package com.qinhao.sort;

import java.util.ArrayList;

/**
 * describ:时间复杂度O（n²）
 *
 * @author qinhao
 * @date 2021/11/23 - 22:29
 */
public class Bubble {
    public static void sort(Comparable[] comparables) {
        for (int i = 0; i < comparables.length; i++) {
            for (int j = 0; j < comparables.length-1; j++) {
                if (comparables[j].compareTo(comparables[j+1]) >= 0) {
                    Comparable temp = comparables[j];
                    comparables[j] = comparables[j+1];
                    comparables[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {564, 45, 45, 15, 15, 0, 778};
        Bubble.sort(arr);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}
