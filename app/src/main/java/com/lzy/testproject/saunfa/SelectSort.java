package com.lzy.testproject.saunfa;

/**
 * Created by LiZhiyu on 2018/11/23.
 * 选择排序
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
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
