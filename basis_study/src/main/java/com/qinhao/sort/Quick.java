package com.qinhao.sort;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/4/17 - 14:30
 */
public class Quick {
    public static void sort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length ; j++) {
                int min = arr[i];
                if (min > arr[j]) {
                    int temp;
                    temp = min;
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
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
