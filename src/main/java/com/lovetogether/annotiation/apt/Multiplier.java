package com.lovetogether.annotiation.apt;

@ExtractInterface("IMultiplier")
public class Multiplier {
    public int multiply(int x, int y) {
        int total = 0;
        for (int i = 0; i < x; i++) {
            total = add(total, y);
        }
        return total;
    }
    private int add(int total, int x) {
        return total + x;
    }

    public static void main(String[] args) {
        Multiplier multiplier = new Multiplier();
        System.out.println("11 * 16 = " + multiplier.multiply(11, 16));
    }
}
