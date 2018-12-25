package com.lovetogether.designpattern.factory.abstractfactory;

import com.lovetogether.designpattern.factory.simple.BMW;
import com.lovetogether.designpattern.factory.simple.Car;

public class BmwDriver implements Driver {
    @Override
    public Car driverCar() {
        return new BMW();
    }
}
