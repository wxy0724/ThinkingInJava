package com.fang.thinkinjava.chapter.xiii.string.regex;

import java.util.Arrays;
import java.util.regex.Pattern;

public class SpliteDemo {

    public static void main(String[] args) {
        String input = "This!!unusual use!!of exclamation!!point";
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input)));
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input, 3)));
    }

}
