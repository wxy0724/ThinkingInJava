package com.fang.myfastjson;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class JSONTest {
    @Test
    public void print() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", 1);
        jsonObject.put("2", 2);
        jsonObject.remove("3", 3);
        User user = new User();
        user.setUserName("Hello");
        jsonObject.put("user", JSONObject.toJSON(user));
        System.out.println(jsonObject);
        String json=jsonObject.toJSONString();
        JSONObject jsonObject1 = JSONObject.parseObject(json);
        jsonObject1.put("123",1);
        System.out.println(jsonObject1);
    }
}
