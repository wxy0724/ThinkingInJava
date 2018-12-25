package com.lovetogether.arithmetic;

import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] arr, int low, int high){
        int i, j, t, temp;
        if (low > high){
            return;
        }

        i = low;
        j = high;
        temp = arr[low];

        while(i < j){
            while(temp <= arr[j] && i < j){
                j--;
            }
            while(temp >= arr[j] && i < j){
                i++;
            }
            if (i < j){
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        arr[low] = arr[j];
        arr[j] = temp;
        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);
    }


    public static int Partition(int[] a, int p, int r){
        int x = a[r - 1];
        int i = p - 1;
        int temp;
        for (int j = p; j <= r - 1; j++) {
            if (a[j - 1] <= x){
                //交换(a[j - 1], a[i - 1]);
                i++;
                temp = a[j - 1];
                a[j - 1] = a[i - 1];
                a[i - 1] = temp;
            }
        }
        //交换(a[r - 1], a[i+1-1])
        temp = a[r - 1];
        a[r - 1] = a[i + 1 - 1];
        a[i + 1 - 1] = temp;
        return i + 1;
    }

    public static void quickSort2(int[] a, int p, int r){
        if(p < r){
            int q = Partition(a, p, r);
            quickSort2(a, p, q - 1);
            quickSort2(a, q + 1, r);
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 8, 4, 3, 6, 1, 10};
        QuickSort.quickSort(arr, 0, arr.length - 1);
        Arrays.stream(arr)
                .forEach(System.out::println);
    }
}
