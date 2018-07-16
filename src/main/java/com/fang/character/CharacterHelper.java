package com.fang.character;

/**
 * 字符串规划工具
 */
public class CharacterHelper {

    public static void main(String[] args) {
        char c = 'c';
        char c1 = CharacterHelper.regularize(c);
        System.out.println("'");
        System.out.println(c1);
    }

    /**
     * 进行字符规格化（全角转半角，大写转小写处理）
     * @param input
     * @return char
     */
    public static char regularize(char input) {
        //char = 12288 代表空格
        int space = 12288;
        int c = 32;
        if(input == space) {
            input = (char) 32;
        }else if (input > 65280 && input < 65375) {
            input = (char) (input - 65248);
        }else if (input >= 'A' && input <= 'Z') {
            input += 32;
        }
        return input;
    }

}
