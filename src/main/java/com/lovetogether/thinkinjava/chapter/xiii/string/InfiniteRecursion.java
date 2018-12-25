package com.lovetogether.thinkinjava.chapter.xiii.string;

import java.util.ArrayList;
import java.util.List;

public class InfiniteRecursion {
    public String toString() {
        return "InfiniteRecursion address" + super.toString() + "\n";
        // 递归调用例子
//        return "InfiniteRecursion address" + this + "\n";
    }
    public static void main(String[] args) {
        List<InfiniteRecursion> v = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            v.add(new InfiniteRecursion());
        }
        System.out.println(v);
    }
}
