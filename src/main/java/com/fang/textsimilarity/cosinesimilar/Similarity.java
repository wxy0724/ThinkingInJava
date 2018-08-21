package com.fang.textsimilarity.cosinesimilar;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 字符串相似性匹配算法
 * 余弦相识度
 * @author xiyao
 */
public class Similarity {

    Map<Character, int[]> vectorMap = new HashMap<>();

    int[] tempArray = null;

    public Similarity(String string1, String string2) {

        for (Character character1 : string1.toCharArray()) {
            if (vectorMap.containsKey(character1)) {
                vectorMap.get(character1)[0]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 1;
                tempArray[1] = 0;
                vectorMap.put(character1, tempArray);
            }
        }
        for (Character character2 : string2.toCharArray()) {
            if (vectorMap.containsKey(character2)) {
                vectorMap.get(character2)[1]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = 1;
                vectorMap.put(character2, tempArray);
            }
        }
    }

    /**
     * 求余弦相似度
      */
    public double sim() {
        double result = 0;
        result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
        return result;
    }

    /**
     * 开平方
     * @param paramMap
     * @return
     */
    private double sqrtMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        result = squares(paramMap);
        result = Math.sqrt(result);
        return result;
    }

    /**
     * 求平方和
      */
    private double squares(Map<Character, int[]> paramMap) {
        double result1 = 0;
        double result2 = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int[] temp = paramMap.get(character);
            result1 += (temp[0] * temp[0]);
            result2 += (temp[1] * temp[1]);
        }
        return result1 * result2;
    }

    /**
     * 点乘法
      */
    private double pointMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int[] temp = paramMap.get(character);
            result += (temp[0] * temp[1]);
        }
        return result;
    }

    public static void main(String[] args) {
        String s1 = "中国吉林省吉林市丰满区江南壹号花园B3号楼2单元26层102号";
        String s2 = "江南壹号花园B3号楼2单元26层102号";
        Similarity similarity = new Similarity(s1, s2);
        System.out.println(similarity.sim());
    }


}
