package com.fang.textsimilarity.mylcs;

import org.apache.commons.lang3.text.StrBuilder;
import org.junit.Test;

public class MyLCS {

    @Test
    public void test1() {
        String s1 = "北京市吉林街庄维花园7号楼";
        String s2 = "北京街庄维花园7号楼";
        System.out.println(MyLCS.longestCommonSubsequence(s2, s1));
        System.out.println(MyLCS.sim(s1, s2));
    }

    /*public static float sim(String s1, String s2){
        String result = MyLCS.longestCommonSubsequence(s2, s1);
        //sim(s1, s2) = MyLCS - Length(s1, s2) / min(Length(s1), Length(s2))
        return 1.0f;
    }*/

    public static float sim(String s1, String s2) {
        String result = MyLCS.longestCommonSubsequence(s2, s1);
        float f = (float) ( result.length() - (s1.length() - s2.length()) ) / Math.min(s1.length(), s2.length());
        return f;
    }

    public static String longestCommonSubsequence(String s1, String s2) {
        int[][] num = new int[s1.length() + 1][s2.length() + 1];// 初始化为0的二维数组

        //实际算法
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    num[i][j] = 1 + num[i - 1][j - 1];
                } else {
                    num[i][j] = Math.max(num[i - 1][j], num[i][j - 1]);
                }
            }
        }
        System.out.println("最长公共子串的长度是：" + num[s1.length()][s2.length()]);

        int s1position = s1.length(), s2position = s2.length();
        StrBuilder result = new StrBuilder();

        while (s1position != 0 && s2position != 0) {
            if (s1.charAt(s1position - 1) == s2.charAt(s2position - 1)) {
                result.append(s1.charAt(s1position - 1));
                s1position--;
                s2position--;
            }
            else if (num[s1position][s2position - 1] >= num[s1position - 1][s2position]) {
                s2position--;
            } else {
                s1position--;
            }
        }
        result.reverse();
        return result.toString();
    }

}
