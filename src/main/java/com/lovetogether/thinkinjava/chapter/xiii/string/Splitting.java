package com.lovetogether.thinkinjava.chapter.xiii.string;

import java.util.Arrays;

public class Splitting {
    public static String knughts = "Then, when you have found the shrubbery, you must cut down the mightiest tree in the forest with... a herring!";

    public static void split(String regex) {
        System.out.println(Arrays.toString(knughts.split(regex)));
    }

    public static void main(String[] args) {
        split(" ");
    }
}
