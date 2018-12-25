package com.lovetogether.java8.stream;

import com.lovetogether.java8.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 一、Stream的三个步骤
 * 1、创建stream
 *
 * 2、中间操作:不会执行任何操作
 *
 * 3、终止操作(终端操作)：一次性执行全部内容，即“惰性求值”
 */
public class TestAPI {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99, Employee.Status.BUSY),
            new Employee("王五", 18, 5555.55, Employee.Status.FREE),
            new Employee("李四", 50, 6666.66, Employee.Status.VOCATION),
            new Employee("赵六", 16, 3333.33, Employee.Status.BUSY),
            new Employee("田七", 16, 7777.77, Employee.Status.FREE),
            new Employee("田七", 8, 7777.77, Employee.Status.VOCATION),
            new Employee("田七", 8, 7777.77, Employee.Status.FREE)
    );

    /**
     * 创建Stream
     */
    @Test
    public void test1(){
        //1、可以通过Collection系列集合提供的stram()或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream01 = list.stream();
        //2、通过Arrays中的静态方法stream()获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream02 = Arrays.stream(emps);
        //3、通过Stream类中的静态方法of()
        Stream<String> stream03 = Stream.of("aa", "bb", "cc");
        //4、创建无限流
        //4-1迭代
        Stream<Integer> stream04 = Stream.iterate(0, (x) -> x + 2);
        stream04.forEach(System.out::println);

        stream04.limit(10).forEach(System.out::println);
        //4-2生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);

    }

    /**
     * 2、中间操作:不会执行任何操作
     * 筛选与切片
         * filter--接收Lamba，从流中排除某些元素
         * limit--截断流，使其元素不超过给定数量
         * skip(n)--跳过元素，返回一个扔掉了前n个元素的流，若流中不足n个，则返回一个空流，与limit互补
         * distinct--筛选，通过所生成元素的hashCode()和equals（）去除重复元素
     */
    @Test
    public void test2(){
        Stream<Employee> s = employees.stream()
                                      .filter((e) -> e.getAge() > 35);
        //内部迭代：迭代操作由Stream API完成
        s.forEach(System.out::println);

    }

    /**
     * 外部迭代
     */
    @Test
    public void test3(){
        Iterator<Employee> it = employees.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    /**
     * limit
     */
    @Test
    public void test4(){
        employees.stream()
                .filter(e -> {
                    System.out.println("短路!");
                    return e.getSalary() > 5000;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    /**
     * skip(n)
     */
    @Test
    public void test5(){
        employees.stream()
                .filter(e -> e.getSalary() > 5000)
                .skip(2)
                .forEach(System.out::println);
    }

    /**
     * distinct: 使用distinct必须重写hashCode()和equals()
     * 去重
     */
    @Test
    public void test6(){
        employees.stream()
                .filter(e -> e.getSalary() > 5000)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射
     * map--接收Lambda，将原始转换成其他形式或提取信息。接收一个函数作为参数，该函式会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap--接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test7(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");

        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);
        //提取员工名字
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        Stream<Stream<Character>> stream = list.stream()
                                               .map(TestAPI::filterCharacter);
        stream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        //flatMap
        Stream<Character> stream1 = list.stream()
                .flatMap(TestAPI::filterCharacter);

        stream1.forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    /**
     * 排序
     * sort：自然排序(Comparable)
     * sorted(Comparator com)--定制排序
     */
    @Test
    public void test8(){
        List<String> list = Arrays.asList("ccc", "aaa", "ddd", "bbb");
        list.stream()
                .sorted()
                .forEach(System.out::println);

        employees.stream()
                .sorted((e1, e2) -> {
                    if (e1.getAge().equals(e2.getAge())){
                        return e1.getName().compareTo(e2.getName());
                    }else{
                        return e1.getAge().compareTo(e2.getAge());
                    }
                })
                .forEach(System.out::println);
        System.out.println("--------------------------------");
        employees.stream().sorted(Comparator.comparing(Employee::getAge)).forEach(System.out::println);

    }
    @Test
    public void test9(){
        Map<Employee.Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));

        map.forEach((k, v) -> {
            System.out.println("key: " + k + ", value:" + v);
        });

    }
    @Test
    public void test10(){
        List<Integer> list = Arrays.asList(1, 7 ,6, 4, 2, 7 ,4, 6, 4, 8, 9);

        list.stream().sorted().forEach(System.out::println);

    }

    @Test
    public void test11(){
        List<Integer> list = Arrays.asList(1, 7 ,6, 4, 2, 7 ,4, 6, 4, 8, 9);
        list = list.stream().filter(i -> i > 5).collect(Collectors.toList());
        list.stream().forEach(System.out::println);

    }



}
