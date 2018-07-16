package com.fang.datastructure.linkedlist;

public class Node {

    //为了方便，这两个变量都使用public，而不用private就不需要写get、set方法了
    //存放数据的变量，简单点，直接为int型
    public int data;
    public Node next;

    //构造方法，在构造时就能够给data赋值
    public Node(int data){
        this.data = data;
    }
}
