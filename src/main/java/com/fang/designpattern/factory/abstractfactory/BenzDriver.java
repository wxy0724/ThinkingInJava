package com.fang.designpattern.factory.abstractfactory;

import com.fang.designpattern.factory.simple.Benz;
import com.fang.designpattern.factory.simple.Car;

public class BenzDriver implements Driver {
    @Override
    public Car driverCar() {
        return new Benz();
    }
}
