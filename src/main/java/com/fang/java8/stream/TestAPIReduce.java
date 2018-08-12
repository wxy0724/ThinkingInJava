package com.fang.java8.stream;

import com.fang.java8.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 归约:将流中元素反复结合起来得到一个值
 * reduce(T identity（起始值）, BinaryOpreator（二元运算）)/reduce(BinaryOperator)
 */
public class TestAPIReduce {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99, Employee.Status.FREE),
            new Employee("王五", 38, 5555.55, Employee.Status.BUSY),
            new Employee("李四", 50, 6666.66, Employee.Status.VOCATION),
            new Employee("赵六", 16, 3333.33, Employee.Status.FREE),
            new Employee("田七", 8, 7777.77, Employee.Status.BUSY)
    );

    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);


        Optional<Double> reduce = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce.get());

    }

    @Test
    public void test2(){
        //总数
        Long collect = employees.stream()
                .collect(Collectors.counting());
        System.out.println(collect);

        //平均值
        Double average = employees.stream()
                .collect(Collectors.averagingDouble(e -> e.getSalary()));
        System.out.println(average);

        //总和
        Double sumSalary = employees.stream()
                .collect(Collectors.summingDouble(e -> e.getSalary()));
        System.out.println("所有员工的工资总和：" + sumSalary);

        //最大值
        Optional<Employee> collect1 = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println("有最大工资的员工： " + collect1.get());

        //最小工资
        Optional<Double> minSalary = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println("最小工资:" + minSalary);
    }

    /**
     * 分组
     */
    @Test
    public void test3(){
        Map<Employee.Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);

    }

    /**
     * 多级分组
     */
    @Test
    public void test4(){
        Map<Employee.Status, Map<String, List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (((Employee) e).getAge() >= 35) {
                        return "青年";
                    } else if (((Employee) e).getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(map);
    }

    /**
     * 分片
     * true一区，false一区
     * 满足条件的一个区，不满足条件的一个区
     */
    @Test
    public void test5(){
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
        System.out.println(map);
    }

    /**
     * 组函数另外的获取方式
     */
    public void test6(){
        DoubleSummaryStatistics dss = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getSum());
    }

    @Test
    public void test7(){
        //连接字符串
        String str = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining());
        System.out.println(str);

        //连接字符串，以，分隔
        String str1 = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", ""/*字符串以什么开始*/, ""/*末尾*/));
        System.out.println(str1);
    }

}