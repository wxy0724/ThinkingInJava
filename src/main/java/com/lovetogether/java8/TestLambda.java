package com.lovetogether.java8;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

public class TestLambda {

    //Lanbda表达式
    public void test2(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    //原来的匿名内部类
    @Test
    public void test1(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> employee = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("王五", 38, 5555.55),
            new Employee("李四", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77)
    );
    //需求:获取当前公司中员工年龄大于35的员工信息
    public List<Employee> filterEmployee(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for (Employee emp: list) {
            if (emp.getAge() >= 35){
                emps.add(emp);
            }
        }
        return emps;
    }

    //需求:获取当前公司中员工工资大于5000的员工信息
    public List<Employee> filterEmployee2(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for (Employee emp: list) {
            if (emp.getSalary() >= 5000){
                emps.add(emp);
            }
        }
        return emps;
    }


    //Java8内置四大核心函数式接口
    //Consume
    public void test(){
        happy(1000, x -> System.out.println("大保健"));
    }
    public void happy(double money, Consumer con){
        con.accept(money);
    }



}
