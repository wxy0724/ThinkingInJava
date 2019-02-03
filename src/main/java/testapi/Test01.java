package testapi;

import com.lovetogether.java8.colletcs.Person;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Test01 {

    public static void main(String[] args) {
        Random random = new Random(47);
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        System.out.println(list.size());
        list.remove(0);
        System.out.println(list.size());

//        for (int i = 0; i < 20; i++) {
//            new Thread(() -> System.out.println(random.nextInt(3))).start();
//        }

    }

    /**
     *  LocalDate转换成Date
     */
    @org.junit.Test
    public void testDateCovertLocalDate() {
        LocalDate ld= LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = ld.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());
        System.out.println("LocalDate = " + ld);
        System.out.println("Date = " + date);
    }

    @org.junit.Test
    public void testBigDecimalEquals() {
        BigDecimal bd = new BigDecimal(0.3);
        System.out.println(bd.doubleValue() == 0.3);
        System.out.println(1.651 / 0.0);
        System.out.println(Double.NaN);
        System.out.println(0.0 == 0);
        System.out.println(Double.NaN);
    }

    @org.junit.Test
    public void testSorted() {
        List<Person> dateList = new ArrayList<>(5);
        Person a = new Person();
        Person b = new Person();
        Person c = new Person();
        Person d = new Person();
        Person e = new Person();
        a.setBirthDay("2018-06");
        b.setBirthDay("2018-09");
        c.setBirthDay("2018-02");
        d.setBirthDay("2018-03");
        e.setBirthDay("2018-12");
        dateList.add(a);
        dateList.add(b);
        dateList.add(c);
        dateList.add(d);
        dateList.add(e);
        List<Person> list = dateList.stream().sorted(Comparator.comparing(Person::getBirthDay)).collect(Collectors.toList());
        list.stream().forEach(System.out::println);
    }

    @org.junit.Test
    public void testNull() {
        Integer i = 123;
        List<Integer> list = new ArrayList<>(10);
        Integer b = null;
        list.add(b);
        System.out.println(new BigDecimal(list.get(0)));
    }

    @org.junit.Test
    public void test8() {
        int count = 0;
        List<Integer> list = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        System.out.println("list.size() = " + list.size());
        for (int i = 0; i < 6; i++) {
            if (list.size() - 1 - i >= 0) {
                count++;
                System.out.println(list.get(i));
            }
        }
        System.out.println("count = " + count);
    }

    @org.junit.Test
    public void test9() {
        String str = "AAACCD";
        System.out.println(str.matches(".*\\)"));
    }

    @Test
    public void test10() {
        Map<String, String> map = new HashMap<>();
        map.put("A", "a");
    }

}
