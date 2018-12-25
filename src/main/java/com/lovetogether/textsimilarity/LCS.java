package com.lovetogether.textsimilarity;

import java.util.Stack;

public class LCS {

    /**
     * 最长公共子串(Longest Common Subsequence, mylcs)
     * 错版： 有时间找找问题
     * @param args
     */
    public static void main(String[] args) {
        // 设置字符串长度
        int sublen1 = 5;
        int sublen2 = 6;
        // 随机生成字符串
        String str1 = "abcbdab";
        String str2 = "bdcaba";
        //二位数组描述矩阵
        int[][] c = new int[sublen1 + 1][sublen2 + 1];
        // 动态规划计算所有子问题
        for (int i = sublen1 - 1; i >= 0; i--) {
            for (int j = sublen2 - 1; j >= 0; j--) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    c[i][j] = c[i + 1][j + 1] + 1;
                } else {
                    c[i][j] = Math.max(c[i + 1][j], c[i][j + 1]);
                }
            }
        }
        System.out.println("str1:" + str1);
        System.out.println("str2:" + str2);
        System.out.print("mylcs:");
        int i = 0, j = 0;
        while (i < sublen1 && j < sublen2) {
            if (str1.charAt(i) == str2.charAt(j)) {
                System.out.print(str1.charAt(i));
                i++;
                j++;
            } else if (c[i + 1][j] >= c[i][j + 1]) {
                i++;
            } else {
                j++;
            }
        }
    }

//    public static void main(String[] args) {
//        String s1 = "abcdab";
//        String s2 = "bdcaba";
//
//        float f = (float) longest(s1, s2).length();
//        float max = (float) Math.max(s1.length(), s2.length());
//
//        System.out.println(f / max);
//
//        System.out.println(longest(s1, s2));
//    }

    public static String longest(String s1,String s2){

        char ch1[]=s1.toCharArray();
        char ch2[]=s2.toCharArray();

        int length1=ch1.length;
        int length2=ch2.length;
        /*
        这里其实还可以考虑一下那个数组较长，在下面创建mat数组时可以节省一点空间，在两个数组长度相差较大时能节省较多空间。
        */
        int max=0;
        int sign=0;
        int[] mat=new int[length1];

        for(int i=0;i<length2;i++){
            for(int j=length1-1;j>=0;j--){
                if(ch2[i]==ch1[j]){
                    if(i==0 || j==0){
                        mat[j]=1;
                        if(max==0){
                            max=1;
                            sign=j;
                        }
                    }

                    else{
                        mat[j]=mat[j-1]+1;
                        if(mat[j]>max){
                            max=mat[j];
                            sign=j;
                        }
                    }
                }
                else{
                    mat[j]=0;
                }
            }
        }
        return new String(ch1, sign-max+1, max);
    }



    private static final String[] CHINESE_NUMBERS = {"零", "一", "两", "三", "四", "五", "六", "七", "八", "九", "十"};

    private static final ChineseUnit[] CHINESE_UNIT = {ChineseUnit.zero, ChineseUnit.ten, ChineseUnit.hundred, ChineseUnit.thousand, ChineseUnit.ten_thousand, ChineseUnit.billion, ChineseUnit.million, ChineseUnit.ten_million, ChineseUnit.hundred_mullion};

    private static final String[] CHINESE_NUMBERS_2 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};

    /**
     *
     * @param number
     * @return
     */
    public static String translateNumber2Chinese(int number) {
        String s = String.valueOf(number);
        if (number <= 10) {
            return CHINESE_NUMBERS[number];
        }
        Stack<NumberUnit> stack = new Stack<>();
        int index = 0;
        for (int i = s.length() - 1; i >= 0; i --) {
            NumberUnit numberUnit = new NumberUnit();
            numberUnit.chineseNumber = CHINESE_NUMBERS_2[Integer.parseInt(String.valueOf(s.charAt(i)))];
            numberUnit.chineseUnit = CHINESE_UNIT[index];
            numberUnit.originalNumber = Integer.parseInt(String.valueOf(s.charAt(i)));
            stack.push(numberUnit);
            index ++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            NumberUnit numberUnit = stack.pop();
            if (numberUnit.originalNumber > 0) {
                stringBuilder.append(numberUnit.chineseNumber);
                if (numberUnit.chineseUnit != ChineseUnit.zero) {
                    stringBuilder.append(numberUnit.chineseUnit.getValue());
                }
            } else if (numberUnit.chineseUnit != ChineseUnit.zero) {
                NumberUnit nextNumber = stack.peek();
                if (nextNumber != null && nextNumber.originalNumber != 0) {
                    stringBuilder.append(numberUnit.chineseNumber);
                }
            }
        }
        return stringBuilder.toString();
    }

    private static class NumberUnit {

        protected ChineseUnit chineseUnit;

        protected String chineseNumber;

        protected int originalNumber;
    }

    enum ChineseUnit {
        /**
         *
         */
        zero("零"),
        /**
         *
         */
        ten("十"),
        /**
         *
         */
        hundred("百"),
        thousand("千"),
        ten_thousand("万"),
        billion("十"),
        million("百"),
        ten_million("千"),
        hundred_mullion("亿")
        ;

        private String value;

        ChineseUnit(String value) {
            this.value = value;
        }

        /**
         * Getter method for property <tt>value</tt>.
         *
         * @return property value of value
         */
        public String getValue() {
            return value;
        }

        /**
         * Setter method for property <tt>value</tt>.
         *
         * @param value  value to be assigned to property value
         */
        public void setValue(String value) {
            this.value = value;
        }
    }

}
