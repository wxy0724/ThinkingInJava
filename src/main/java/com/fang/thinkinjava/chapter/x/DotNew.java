package com.fang.thinkinjava.chapter.x;

public class DotNew {
    public class Innew{}

    public static void main(String[] args) {
        DotNew dn = new DotNew();
        DotNew.Innew dni = dn.new Innew();
    }
}
