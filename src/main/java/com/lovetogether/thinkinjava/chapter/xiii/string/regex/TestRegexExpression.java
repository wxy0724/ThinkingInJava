package com.lovetogether.thinkinjava.chapter.xiii.string.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegexExpression {

    public static void main(String[] args) {
        args = new String[]{"abcabcabcdefabc", "abc+", "(abc)+", "(abc){2,}"};
//        args[0] = "abcabcabcdefabc";
//        args[1] = "abc+";
//        args[2] = "(abc)+";
//        args[3] = "(abc){2,}";

        if (args.length < 2) {
            System.out.println("Usage:\njava TestRegexExpression " + "characterSequence regularExpression+");
            System.exit(0);
        }
        System.out.println("Input: \"" + args[0] + "\"");
        for (String arg : args) {
            System.out.println("Regular expression: \"" + arg + "\"");
            Pattern pattern = Pattern.compile(arg);
            Matcher matcher = pattern.matcher(args[0]);
            while (matcher.find()) {
                System.out.println("Match \"" + matcher.group() + "\" at positions " + matcher.start() + " - " + (matcher.end() - 1));
            }
        }
    }

}
