package com.lovetogether.java8.stream;

import com.lovetogether.java8.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 终止操作
 */
public class TestAPITermination {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99, Employee.Status.FREE),
            new Employee("王五", 38, 5555.55, Employee.Status.BUSY),
            new Employee("李四", 50, 6666.66, Employee.Status.VOCATION),
            new Employee("赵六", 16, 3333.33, Employee.Status.FREE),
            new Employee("田七", 8, 7777.77, Employee.Status.BUSY)
    );

    /**
     * 查找与匹配
     * allMatch--检查是否匹配所有元素
     * anyMatch--检查是否至少匹配一个元素
     * noneMathc--检查是否没有匹配所有元素
     * findFirst--返回 第一个元素
     * findAny--返回当前流中的任意元素
     * count--返回流中元素的总个数
     * max--返回流中最大值
     * min--返回流中最小值
     */

    /**
     * allMatch--检查是否匹配所有元素
     */
    @Test
    public void test1(){
        boolean b1 = employees.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);
    }

    /**
     * anyMatch--检查是否至少匹配一个元素
     *
     *
     */
    @Test
    public void test2(){
        boolean b1 = employees.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);
    }

    /**
     * noneMathc--检查是否没有匹配所有元素
     */
    @Test
    public void test3(){
        boolean b1 = employees.stream()
                .noneMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);
    }

    /**
     * findFirst--返回 第一个元素
     */
    @Test
    public void test4(){
        // Optional， 容器类， java8为了避免空指针
        Optional<Employee> op = employees.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        //Optional封装的对象为空可以找一个替换的，类似 SQL中的if……nor
        //op.orElse();
        System.out.println(op.get());
    }

    /**
     * findAny--返回当前流中的任意元素
     */
    @Test
    public void test5(){
        Optional<Employee> any = employees.stream()
                .filter(e -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(any.get());

        //并行流,多个线程找，谁先找到先返回谁
        Optional<Employee> any1 = employees.parallelStream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(any1.get());
    }

    /**
     * count--返回流中元素的总个数
     */
    @Test
    public void test6(){
        long count = employees.stream()
                .count();
        System.out.println(count);
    }

    /**
     * max--返回流中最大值
     */
    @Test
    public void test7(){
        Optional<Employee> max = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());
    }

    /**
     * min--返回流中最小值
     */
    @Test
    public void test8(){
        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(min.get());
    }
}
