package com.lzy.testproject.saunfa;

/**
 * 快排，平均时间复杂度N*logN
 * 把一组数据以一个基准数分成两部分，前一部分全部比这个基准数小，后一部分全部比基准数大；然后分别对这两部分递归使用这种方式来排序
 */
public class KuaiPai {

    public static void main(String[] args) {
        int[] array = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        kuaipai(0, array.length - 1, array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
    }

    private static void kuaipai(int start, int end, int[] array) {
        if (start > end) {
            return;
        }
        int i = start;
        int j = end;
        int base = array[start];//最左边的作为基准数
        while (i < j) {
            while (array[j] >= base && i < j)//先从右往左找，直到找到小于基准数的下标为j的为止
                j--;
            while (array[i] <= base && i < j)//再从左往右找，知道找到大于基准数
                i++;
            //把这两个数交换位置，然后继续下一次循环，直到i和j相遇
            if (i < j) {
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        //当i和j相遇时，结束了循环，把基准数放到i的位置，实现了左边所有比基准数小，右边所有比基准数大
        int temp = array[start];
        array[start] = array[i];
        array[i] = temp;

        //再次对左边和右边采用相同的方法递归实现
        kuaipai(start, i - 1, array);
        kuaipai(i + 1, end, array);
    }
}
