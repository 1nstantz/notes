package com.qinhao.sort;

/**
 * describ
 *
 * @author qinhao
 * @date 2021/11/23 - 22:46
 */
public class Selection {
    public static void sort(Comparable[] comparables) {
        for (int i = 0; i < comparables.length; i++) {
            for (int j = i; j < comparables.length; i++) {
                if (comparables[i].compareTo(comparables[j]) >= 0) {
                    Comparable temp = comparables[i];
                    comparables[i] = comparables[j];
                    comparables[j] = temp;
                }
            }
        }
    }
}
