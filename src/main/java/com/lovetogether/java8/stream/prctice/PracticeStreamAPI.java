package com.lovetogether.java8.stream.prctice;

import com.lovetogether.java8.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PracticeStreamAPI {

    /**
    * 1、给定一个字符列表，如何返回一个由每个数字的平方构成的列表？
    * 给定【1， 2， 3， 4， 5,】，应该返回【1， 4， 9， 16， 25】
    */
    @Test
    public void test1(){
        Integer[] nums = {1, 2, 3, 4, 5};
        Arrays.stream(nums)
                .map(i -> i * i)
                .forEach(System.out::println);
    }

    /**
     * 2、怎么用map和reduce方法数一数流中有多少个Employee
     */
    @Test
    public void test2(){
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9999.99, Employee.Status.FREE),
                new Employee("王五", 38, 5555.55, Employee.Status.BUSY),
                new Employee("李四", 50, 6666.66, Employee.Status.VOCATION),
                new Employee("赵六", 16, 3333.33, Employee.Status.FREE),
                new Employee("田七", 8, 7777.77, Employee.Status.BUSY)
        );

        Optional<Integer> reduce = employees.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println(reduce.get());
    }
}
