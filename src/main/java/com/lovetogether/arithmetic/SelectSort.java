package com.lovetogether.arithmetic;

/**
 * 每一次从待排序的数据元素中选出最小（或最大）的一个元素，
 * 存放在序列的起始位置，直到全部待排序的数据元素排完。
 * 选择排序是不稳定的排序方法
 * （比如序列[5， 5， 3]第一次就将第一个[5]与[3]交换，导致第一个5挪动到第二个5后面）。
 */
public class SelectSort {
    public static void selectSort(int[] arr){
        int minIndex = 0;
        int temp = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;//无序区的最小数组下标
            for (int j = i + 1; j < arr.length; j++) {
                //无序区中找到最小数据并保存期数组下标
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            //将最小元素放到本次循环的前端
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {12,5,6,87,32,45,65};
        SelectSort.selectSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
