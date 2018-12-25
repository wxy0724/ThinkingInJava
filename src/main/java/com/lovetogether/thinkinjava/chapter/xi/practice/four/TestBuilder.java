package com.lovetogether.thinkinjava.chapter.xi.practice.four;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author wangxiyao
 */
public class TestBuilder {

    static String[] movies = {"DeadPool", "War Star", "Superman", "Iron Man", "The shawshank redemption"};

    static Collection next(Collection<String> collection, int index) {
        int endTime = 0;
        for (int i = 0; i < index; i++) {
            endTime += 1;
            if (i >= movies.length) {
                i = i - movies.length;
            }
            collection.add(movies[i]);
            if (endTime == index) {
                break;
            }
        }
            return collection;
    }

    public static void main(String[] args) {
        System.out.println(next(new ArrayList<String>(), movies.length * 2));
    }
}
