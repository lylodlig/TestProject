package com.lzy.testproject.saunfa;

/**
 * Created by LiZhiyu on 2018/11/23.
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = {3, 1, 3, 5, 8, 12, 4, 2};
        int[] ints = selectSort(array);
    }

    public static int[] selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
                //把最小的与未排序第一个交换
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
        return array;
    }
}
