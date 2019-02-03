package com.lovetogether.myenum;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

enum Explore {
    HERE, THERE
}
public class Reflection {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Reflection.class);

    public static Set<String> analyze(Class<?> enumClass) {
        System.out.println("-----------Analyzing " + enumClass + "--------------------");
        System.out.println("Interfaces: ");
        for (Type type : enumClass.getGenericInterfaces()) {
            System.out.println(type);
        }
        System.out.println("Base: " + enumClass.getSuperclass());
        System.out.println("Method: ");
        Set<String> methods = new TreeSet<>();
        for (Method method : enumClass.getMethods()) {
            methods.add(method.getName());
        }
        System.out.println(methods);
        return methods;
    }

    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        System.out.println("Explore.containsAll(Enum)?" + exploreMethods.containsAll(enumMethods));
        System.out.println("exploreMethods.removeAll(enumMethods) : ");
        exploreMethods.removeAll(enumMethods);
        System.out.println(exploreMethods);
    }
}
