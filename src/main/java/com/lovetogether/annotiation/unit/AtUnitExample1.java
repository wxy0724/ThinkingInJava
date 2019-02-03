package com.lovetogether.annotiation.unit;

public class AtUnitExample1 {

    public String methodOne() {
        return "This is methodOne";
    }

    public int methodTwo() {
        System.out.println("This is methodOne");
        return 2;
    }

    boolean methodOneTest() {
        return methodOne().equals(methodOne());
    }


}
