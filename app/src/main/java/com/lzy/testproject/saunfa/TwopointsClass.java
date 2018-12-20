package com.lzy.testproject.saunfa;

/**
 * Created by LiZhiyu on 2018/12/19.
 * 待查表一定要是有序，在给出的有序排列的数组中，把目标值和数组中间值进行比较，如果相等，则返回中间值下标，如果目标值小于中间值，就从数
 *  组的前半段再次执行二分法查找，如果目标值大于中间值，从数组的后半段开始二分法查找
 */
public class TwopointsClass {
    public static void main(String[] args) {
        //有序排列数组（大到小，小到大无所谓）
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        //打印二分法的返回值
        System.out.println(searchRecursive(array,0,array.length-1,9));
    }

    public static int searchRecursive(int[] array,int start,int end,int findValue){

        if(array==null){
            return -1;
        }
        if(start<=end){
            //中间位置
            int middle = (start + end)/1;
            //中值
            int middleValue = array[middle];

            if(findValue == middleValue){
                //与中值相等就直接返回
                return middle;
            }else if(findValue < middleValue){
                //目标值小于中值，在中值前面找（这里调用了二分法的方法）
                return searchRecursive(array,start,middle - 1,findValue);
            }else {
                //目标值大于中值，在中值后面找（这里调用了二分法的方法）
                return searchRecursive(array,middle + 1,end,findValue);
            }

        }else{
            //返回-1，查找失败
            return -1;
        }
    }
}
