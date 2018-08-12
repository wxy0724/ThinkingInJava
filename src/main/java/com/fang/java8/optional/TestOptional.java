package com.fang.java8.optional;

import com.fang.java8.Employee;
import com.fang.java8.bean.Godness;
import com.fang.java8.bean.Man;
import com.fang.java8.bean.NewMan;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional容器类的常用方法
 * Optional.of(T t)：一个Optional实例
 * Optional.empty(): 创建一个空的Optional实例
 * Optional.ofNullable(T t):若t不为null，创建Optional实例，否则创建空实例
 * isPresent:判断是否包含值
 * orElse(T t): 如果调用对象包含值，返回该值，否则返回t
 * orElseGet(Supplier s):如果调用对象包含值，返回该值，否则返回s获取的值
 * map(Function f):如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
 * flatMap(Function mapper):与map类似，要求返回值必须是Optional
 */
public class TestOptional {

    /**
     * 例题
     */
    @Test
    public void test5(){
        Optional<NewMan> man = Optional.ofNullable(null);
        String godnessName = getGodnessName2(man);
        System.out.println(godnessName);
    }

    /**需求：获取一个男人心中女神的名字
     *
     * @param man
     * @return
     */
    public String getGodnessName(Man man){
        return man.getGodness().getName();
    }

    public String getGodnessName2(Optional<NewMan> man){
        return man.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("苍井空"))
                .getName();

    }

    @Test
    public void test4(){
        Optional<Employee> op = Optional.ofNullable(new Employee("张三", 18, 8888.88, Employee.Status.BUSY));
        Optional<String> str = op.map((e) -> e.getName());
        System.out.println(str.get());

        Optional<String> str2 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(str2.get());
    }

    @Test
    public void test3(){
        Optional<Employee> op = Optional.ofNullable(null);
        if (op.isPresent()){
            System.out.println(op.get());
        }

        Employee employee = op.orElse(new Employee("张三", 18, 8888.88, Employee.Status.BUSY));
        System.out.println(employee);

        Employee emp = op.orElseGet(() -> new Employee());


    }

    @Test
    public void test2(){
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.get());
    }

    @Test
    public void test1(){
        //不能通过null构建Optional
        Optional<Employee> op = Optional.of(new Employee());
        Employee employee = op.get();
        System.out.println(employee);
    }

}
