package com.lovetogether.java8;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestMethodRef {

    /**
     * 接口的参数列表与返回值与Lambda体中的方法一致
     * 对象::实例方法名
     */
    @Test
    public void test1(){
        Consumer<String> con = (x) -> System.out.println(x);

        PrintStream ps1 = System.out;
        Consumer<String> con0 = (x) -> ps1.println(x);

        PrintStream ps = System.out;
        Consumer<String> con1 = ps::println;
    }

    @Test
    public void test2(){
        Employee emp = new Employee();
        Supplier<String> sup = () -> emp.getName();

        Supplier<Integer> sup2 = emp::getAge;
    }

    @Test
    public void test3(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> com2 = Integer::compare;
        int i = com.compare(3, 5);
        System.out.println(i);
    }

    @Test
    public void test4(){
        BiPredicate<String, String> bp = (x ,y) -> x.equals(y);

        BiPredicate<String, String> bp1 = String::equals;
    }

    /**
     * 构造器引用
     */
    @Test
    public void test5(){
        Supplier<Employee> sup = () -> new Employee();
        Employee employee = sup.get();

        //构造器语法：
        Supplier<Employee> emp = Employee::new;
    }

    /**
     * 数组引用
     */
    public void test6(){
        Function<Integer, String[]> fun = (x) -> new String[x];
        Function<Integer, String[]> fun2 = String[]::new;
    }
}
