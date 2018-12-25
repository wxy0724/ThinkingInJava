package com.lovetogether.designpattern.factory.abstractfactory;

import com.lovetogether.designpattern.factory.simple.Benz;
import com.lovetogether.designpattern.factory.simple.Car;

public class BenzDriver implements Driver {
    @Override
    public Car driverCar() {
        return new Benz();
    }
}
