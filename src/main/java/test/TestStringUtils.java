package test;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TestStringUtils {

    @Test
    public void test1() {
        String s = "";
        boolean a = StringUtils.isNotBlank("");
        boolean b = StringUtils.isNotBlank(" ");
        boolean c = StringUtils.isNotBlank(null);
        boolean d = StringUtils.isNotBlank("null");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

    @Test
    public void test2() {
        String integer = "13000.00";

        Integer i = Integer.valueOf(integer.substring(0, integer.indexOf(".")));
        System.out.println(i);
    }

    @Test
    public void test3() {
        String num = "123";

        System.out.println(StringUtils.isNumeric(num));
    }

    @Test
    public void test4() {
//        System.out.println(RandomStringUtils.randomNumeric(5));
//        System.out.println(RandomStringUtils.random(8, true, true));
        System.out.println(RandomStringUtils.random(1, 65, 91, true, true));
    }

    @Test
    public void test5() {
        Set<String> set = new HashSet<>();
        CompletableFuture.runAsync(() -> test4());
        for (int i = 0; i < 10000; i++) {
            set.add(RandomStringUtils.random(2, 65, 91, true, false).substring(0, 1));
        }
        System.out.println(set.size() + " : " + set.toString());
    }

    @Test
    public void test6() {
        new Thread(() -> System.out.println("sleep 10 s")).start();
    }



    static void pong() {
        System.out.print("pong");
    }

    public static void main(String[] args) {
        Thread t = new Thread() {
            public void run() {
                pong();
            }
        };
        t.start();
        System.out.println("ping");
    }

}
