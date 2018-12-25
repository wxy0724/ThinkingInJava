package com.lovetogether.designpattern.factory.simple;

public class Boss {

    public static void main(String[] args) {
        Car car = Driver.driveCar("BMW");
        car.drive();
    }

}
