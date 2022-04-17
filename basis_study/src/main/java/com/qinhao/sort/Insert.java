package com.qinhao.sort;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/4/17 - 14:43
 */
public class Insert {
    public static void sort(int[] array) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            int target = array[i];
            int j = i-1;
            while (j >= 0 && array[j] > target) {
                array[j + 1] = array[j];
                j--;
            }
            array[j+1] = target;
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
