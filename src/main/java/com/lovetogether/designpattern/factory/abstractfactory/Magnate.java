package com.lovetogether.designpattern.factory.abstractfactory;

import com.lovetogether.designpattern.factory.simple.Car;

public class Magnate {
    public static void main(String[] args) {
        Driver d = new BenzDriver();
        Car car = d.driverCar();
        car.drive();
    }
}
