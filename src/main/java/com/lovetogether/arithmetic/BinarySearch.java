package com.lovetogether.arithmetic;

//前提:必须是有序
public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {0,3,4,6,9,12,15,17,71};
        int i = binartSearch(array, 4);
        System.out.println("index 4 :" + i);
    }

    public static int binartSearch(int[] array, int key){
        int left = 0;
        int right = array.length;

        while(left <= right){
            int mid = (left + right)/2;
            if(array[mid] == key){
                return mid;
            }else if(array[mid] < key){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return -1;
    }
}
