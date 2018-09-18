package test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

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

}
