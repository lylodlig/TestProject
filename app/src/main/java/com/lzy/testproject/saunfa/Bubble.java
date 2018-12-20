package com.lzy.testproject.saunfa;

/**
 * 冒泡排序
 * 基本思想：每次比较相邻的两个元素，如果顺序错误就交换，一次循环后确定一个元素的位置，时间复杂度为O(N的平方)
 */
public class Bubble {

    public static void main(String[] args) {
        int[] array = {3, 1, 12, 32, 22, 67, 42};
        int[] bubble = bubble2(array);
        for (int i = 0; i < bubble.length; i++) {
            System.out.print(bubble[i] + "\t");
        }
    }

    public static int[] bubble(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 优化：如果又一次循环，没有发生位置交换，说明已经排好，直接退出循环
     */
    public static int[] bubble2(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean isChanged = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    isChanged = true;
                }
            }
            if (!isChanged)
                break;
        }
        return array;
    }
}
