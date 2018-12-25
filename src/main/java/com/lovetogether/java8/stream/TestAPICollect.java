package com.lovetogether.java8.stream;


import com.lovetogether.java8.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 收集
 * collect--将流转换为其他形式，接受一个Collect接口的实现，用于个Strem中元素做汇总方法
 */
public class TestAPICollect {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99, Employee.Status.FREE),
            new Employee("王五", 38, 5555.55, Employee.Status.BUSY),
            new Employee("李四", 50, 6666.66, Employee.Status.VOCATION),
            new Employee("赵六", 16, 3333.33, Employee.Status.FREE),
            new Employee("田七", 8, 7777.77, Employee.Status.BUSY),
            new Employee("田七", 8, 7777.77, Employee.Status.BUSY)

    );

    @Test
    public void test1(){
        List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
    }
}
