package com.fang.designpattern.factory.abstractfactory;

import com.fang.designpattern.factory.simple.Car;

public class Magnate {
    public static void main(String[] args) {
        Driver d = new BenzDriver();
        Car car = d.driverCar();
        car.drive();
    }
}
