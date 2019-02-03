package com.lovetogether.annotiation;


public class Testable {
    public void execute() {
        System.out.println("Execute...");
    }
    @Test void testExecute() {
        execute();
    }
}
