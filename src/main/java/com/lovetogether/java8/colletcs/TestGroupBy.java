package com.lovetogether.java8.colletcs;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestGroupBy {

    @Test
    public void test1() {
        System.out.println("=======groupingBy==========");
        Stream<Person> stream = Stream.of(new Person("1", "aa", "12"), new Person("1", "bb", "13"), new Person("3", "cc", "14"));
        System.out.println(stream.collect(Collectors.groupingBy(x -> x.id)));

        //groupingBy
        Map<String, List<Person>> tempMap = Stream.of(new Person("1", "aa", "12"), new Person("1", "bb", "13"), new Person("3", "cc", "14"))
                .collect(Collectors.groupingBy(x -> x.id));
        for (String s : tempMap.keySet()) {
            tempMap.get(s).stream().forEach(x -> System.out.println(x));
        }

        Map<Boolean, List<Integer>> collectGroup = Stream.of(1, 2, 3, 4)
                .collect(Collectors.groupingBy(it -> it > 3));
        System.out.println("collectGroup : " + collectGroup);
    }

    @Test
    public void test2() {
        //1.分组计数
        List<Person> list1 = Arrays.asList(new Person("1", "aa", "12"), new Person("1", "bb", "13"), new Person("3", "cc", "14"));
        //1.1根据某个属性分组计数
        Map<String,Long> result1 = list1.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        System.out.println(result1);
        //1.2根据整个实体对象分组计数,当其为String时常使用
        Map<Person,Long> result2=list1.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(result2);
        //1.3根据分组的key值对结果进行排序、放进另一个map中并输出
        Map<String,Long> xMap=new HashMap<>();
        result1.entrySet().stream().sorted(Map.Entry.<String,Long>comparingByKey().reversed()) //reversed不生效
                .forEachOrdered(x->xMap.put(x.getKey(),x.getValue()));
        System.out.println(xMap);

        //2.分组，并统计其中一个属性值得sum或者avg:id总和
       /* Map<String,Integer> result3=list1.stream().collect(
                Collectors.groupingBy(Person::getAge,Collectors.summingInt(Person::getId))
        );
        System.out.println(result3);*/

    }

}
