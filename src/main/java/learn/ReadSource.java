package learn;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReadSource {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>(16);
        String a = map.put(1, "A");
        System.out.println(a);
        System.out.println("map.size: " + map.size());
        System.out.println((2 ^ 16));
        // >>>无符号右移  >>有符号右移
        System.out.println(32 >> 4);
        System.out.println("MAX INT: " + (1 << 30) );
        System.out.println(Integer.parseInt("100000", 2));
    }

    @Test
    public void testHashSet() {
        Set<String> set = new HashSet<>();
        boolean a = set.add("A");
        System.out.println(a);
        boolean a1 = set.add("A");
        System.out.println(a1);
    }

}
