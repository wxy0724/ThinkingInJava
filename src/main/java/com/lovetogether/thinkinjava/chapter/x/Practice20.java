package com.lovetogether.thinkinjava.chapter.x;

public interface Practice20 {
    String helloWorld();
    class Test implements Practice20 {
        public static void main(String[] args) {
            Test t = new Test();
            helloWorld(t);
        }
        public static void helloWorld(Practice20 p){
            System.out.println(p.helloWorld());
        }

        @Override
        public String helloWorld() {
            return "HelloWorld";
        }
    }
}
