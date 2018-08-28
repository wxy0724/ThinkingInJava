package com.fang.chapter.xi;

import java.util.*;

public class AddingGroups {

    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer[] moreInts = {6, 7, 8, 9, 10};

        // Runs significantly faster, but you can`t
        // construct a Collection this way
        Collections.addAll(collection, 11, 12, 13, 14, 15);
        Collections.addAll(collection, moreInts);

        List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);
        // OK -- modify an element
        list.set(1, 99);
        // Runtime error because the underlying array cannot be resized, Exception in thread "main" java.lang.UnsupportedOperationException
        // Arrays.asList的输出可以将其当做List,但是其底层表示的是数组，因此不但能调整尺寸
        // 如果使用add()或delete()方法，可能会引发改变数组尺寸的尝试，
        // 因此将在运行时得到：Exception in thread "main" java.lang.UnsupportedOperationException
        // list.add(21);
        collection.forEach(System.out::println);
        list.forEach(System.out::println);
    }

}
