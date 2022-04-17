package com.qinhao.sort;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/4/17 - 14:09
 */
public class Bubble {
    public static void sort(int[] array) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            boolean flag = true;
            for (int j = 0; j < length-i ; j++) {
                if (array[j] > array[j + 1]) {
                    int temp ;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j +1] =temp;
                    flag = false;
                }
            }
            if (flag) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 9, 3, 5, 7};
        sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

}
