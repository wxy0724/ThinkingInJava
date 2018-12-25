package com.lovetogether.thinkinjava.chapter.xiii.string.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Restting {
    public static void main(String[] args) {

        Matcher m = Pattern.compile("[frb][aiu][gx]").matcher("fix the rug with bages");
        System.out.println(m.find(0));
        System.out.println(m.group(0));
        while (m.find()) {
            System.out.print(m.group() + " ");
        }

        System.out.println();
        m.reset("fix the rig with rags");
        while (m.find()) {
            System.out.print(m.group() + " ");
        }
    }
}
