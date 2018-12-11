package test;

import java.util.HashMap;
import java.util.Map;

public class TestMapReference {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>(3);
        Map<String, Object> innnerMap = new HashMap<>(3);
        map.put("inner", innnerMap);
        innnerMap.put("1", 1);
        innnerMap.put("2", 2);
        innnerMap.put("3", 3) ;

        map.put("outter", innnerMap);
        innnerMap.put("1", 4);
        innnerMap.put("2", 5);
        innnerMap.put("3", 6) ;
    }
}
