package com.lovetogether.designpattern.factory.simple;

/**
 * 工厂类
 * 不在关注要什么产品，直接消费
 * 开闭原则：对扩展开放，对修改封闭
 */
public class Driver {
    public static Car driveCar(String car) {
        if (car.equals("BMW")) {
            return new BMW();
        } else if (car.equals("Benz")) {
            return new Benz();
        }
        return null;
    }
}
