package com.lovetogether.thinkinjava.chapter.x;

public class TestParcel {
    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Contents c = p.contens();
        Destination d = p.destination("Tasmania");
    }
}
