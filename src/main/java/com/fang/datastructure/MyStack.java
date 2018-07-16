package com.fang.datastructure;

//栈是限定仅在表头进行插入和删除的线性表
//后进先出
//用数组实现栈的基本操作:压栈、弹栈等
public class MyStack {
    //底层用数组实现
    private long[] arr;
    //栈顶指针
    private int pop;

    public MyStack(){
        arr = new long[10];
        pop = -1;
    }

    public MyStack(int maxsize){
        arr = new long[maxsize];
        pop = -1;
    }

    //入栈
    public void push(long data){
        arr[++pop] = data;
    }

    //弹栈
    public long pop(){
        return arr[pop--];
    }

    //查看栈顶数据
    public long peek(){
        return arr[pop];
    }
    /**
    * 遍历数组
    * @return
    */
    public String list(){
        String str = "[";
        for (int i = 0; i <= pop; i++){
            str += arr[i] + ", ";
        }
        str = str.substring(0, str.length()) + "]";
        return str;
    }

    @Override
    public String toString(){
        return list();
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack(20);

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println(stack);
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack);
    }
}
