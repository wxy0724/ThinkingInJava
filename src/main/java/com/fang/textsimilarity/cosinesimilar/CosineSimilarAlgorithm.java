package com.fang.textsimilarity.cosinesimilar;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * 方案一:
 * 1, 规整地址后(去掉重复省市, 特殊符号,数字,字母)
 * 2, 结合上面的代码
 *
 * 方案二:
 * 1, 规整地址后(去掉重复省市, 特殊符号,数字,字母)
 * 2, 结合上面的代码(加入分词概念), 地址词库的完整直接关系到判断的准确性.
 *
 * @author xiyao
 */
public class CosineSimilarAlgorithm {

    public static double getSimilarity(String content1, String content2) {
        Map<Integer, int[]> wordMap = new HashMap<>(16);

        // 将两个字符串中的中文字符以及出现的总数封装到，AlgorithmMap中
        char[] char1 = content1.toCharArray();
        for (int i = 0; i < char1.length; i++) {
            if(isHanZi(char1[i])){
                int charIndex = getGB2312Id(char1[i]);
                if(charIndex != -1){
                    int[] fq = wordMap.get(charIndex);
                    if(fq != null && fq.length == 2){
                        fq[0]++;
                    }else {
                        fq = new int[2];
                        fq[0] = 1;
                        fq[1] = 0;
                        wordMap.put(charIndex, fq);
                    }
                }
            }
        }

        char[] char2 = content2.toCharArray();
        for (int i = 0; i < char2.length; i++) {
            if(isHanZi(char2[i])){
                int charIndex = getGB2312Id(char2[i]);
                if(charIndex != -1){
                    int[] fq = wordMap.get(charIndex);
                    if(fq != null && fq.length == 2){
                        fq[1]++;
                    }else {
                        fq = new int[2];
                        fq[0] = 0;
                        fq[1] = 1;
                        wordMap.put(charIndex, fq);
                    }
                }
            }
        }

        Iterator<Integer> iterator = wordMap.keySet().iterator();
        double sqdoc1 = 0;
        double sqdoc2 = 0;
        double denominator = 0;
        while(iterator.hasNext()){
            int[] c = wordMap.get(iterator.next());
            denominator += c[0]*c[1];
            sqdoc1 += c[0]*c[0];
            sqdoc2 += c[1]*c[1];
        }
        return denominator / Math.sqrt(sqdoc1*sqdoc2);
    }

    public static boolean isHanZi(char ch) {
        // 判断是否汉字
        return (ch >= 0x4E00 && ch <= 0x9FA5);

    }

    /**
     * 根据输入的Unicode字符，获取它的GB2312编码或者ascii编码，
     *
     * @param ch 输入的GB2312中文字符或者ASCII字符(128个)
     * @return ch在GB2312中的位置，-1表示该字符不认识
     */
    public static short getGB2312Id(char ch) {
        try {
            byte[] buffer = Character.toString(ch).getBytes("GB2312");
            if (buffer.length != 2) {
                // 正常情况下buffer应该是两个字节，否则说明ch不属于GB2312编码，故返回'?'，此时说明不认识该字符
                return -1;
            }
            // 编码从A1开始，因此减去0xA1=161
            int b0 = (int) (buffer[0] & 0x0FF) - 161;
            // 第一个字符和最后一个字符没有汉字，因此每个区只收16*6-2=94个汉字
            int b1 = (int) (buffer[1] & 0x0FF) - 161;
            return (short) (b0 * 94 + b1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        String address1 = "湖北省-武汉市-东西湖区 革新大道四明路物流园c5门";
        String address2 = "湖北省-武汉市-东西湖区 革新大道四明路南特1号";
        System.out.println(getSimilarity(address1, address2));
    }

}
