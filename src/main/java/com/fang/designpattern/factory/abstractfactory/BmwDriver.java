package com.fang.designpattern.factory.abstractfactory;

import com.fang.designpattern.factory.simple.BMW;
import com.fang.designpattern.factory.simple.Car;

public class BmwDriver implements Driver {
    @Override
    public Car driverCar() {
        return new BMW();
    }
}
