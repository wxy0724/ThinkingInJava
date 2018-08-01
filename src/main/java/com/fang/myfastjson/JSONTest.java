package com.fang.myfastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

public class JSONTest {

    /**
     * json字符串-简单对象型
     */
    private static final String  JSON_OBJ_STR = "{\"userName\":\"lily\",\"age\":12}";
    /**
     * json字符串-数组类型
     */
    private static final String  JSON_ARRAY_STR = "[{\"userName\":\"lily\",\"age\":12},{\"userName\":\"lucy\",\"age\":15}]";
    /**
     * 复杂格式json字符串
     */
    private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";
    /**
     * 超级复杂格式json字符串
     */
    private static final String  SUPER_COMPLEX_JSON_STR = "{\"addrsList\":[{\"addrList\":[{\"city\":\"北京\",\"district\":\"\",\"handlerWay\":\"termQuerySearchtype\",\"isMulti\":\"N\",\"projCode\":\"1010737483\",\"projName\":\"北京方向\",\"setBuildingSuffixs\":false,\"setBuildings\":false,\"setCity\":true,\"setDistrict\":true,\"setDoornumberSuffixs\":false,\"setDoornumbers\":false,\"setFloorSuffixs\":false,\"setFloors\":false,\"setHandlerWay\":true,\"setIsMulti\":true,\"setNowDistrict\":false,\"setProjCode\":true,\"setProjName\":true,\"setProvince\":false,\"setStreetNums\":false,\"setStreets\":false,\"setUnitSuffixs\":false,\"setUnits\":false,\"setVillageSuffixs\":false,\"setVillages\":false}],\"addrListIterator\":{},\"addrListSize\":1,\"id\":\"5589\",\"key\":\"北京方向\",\"setAddrList\":true,\"setId\":true,\"setKey\":true}],\"addrsListIterator\":{},\"addrsListSize\":1,\"setAddrsList\":true}";

    /**
     * 简单javaBean与json对象之间的转换
     */
    @Test
    public void testJavaBeanToJSONObject(){
        //JavaBean
        User user = new User("Jack", 23);

        //方式一:
        String jsonString = JSONObject.toJSONString(user);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        System.out.println("方式一：" + jsonString);

        //方式二:
        String jsonString2 = JSONObject.toJSONString(user);
        System.out.println("方式二：" + jsonString2);
    }

    /**
     * json字符串（数组类型）与JSONArray之间的转换
     */
    @Test
    public void testJSONStrToJSONArray(){
        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);

        //遍历方式一
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println("遍历方式一: userName: " + jsonObject.getString("userName") + "age: " +jsonObject.getInteger("age"));
        }

        //遍历方式二：
        for (Object obj: jsonArray) {
            JSONObject jsonObject = (JSONObject)obj;
            System.out.println("遍历方式二: userName: " + jsonObject.getString("userName") + "age: " +jsonObject.getInteger("age"));
        }
    }

    /**
     * JSONArray到json字符串-数组类型的转换
     */
    @Test
    public  void testJSONArrayToJSONStr(){
        //已知JSONArray,目标要转换为json字符串
        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);

        //方式一：
        String jsonString = JSONArray.toJSONString(jsonArray);
        System.out.println("方式一：" + jsonString);

        //方式二:
        String jsonString2 = jsonArray.toJSONString(jsonArray);
        System.out.println("方式二：" + jsonString2);
    }
    /**
     * 简单json对象到JavaBean的转换
     */
    @Test
    public void testJSONObjectToJavaBean(){
        //已知简单json对象
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);

        //方式一：使用TypeReference<T>类，由于其构造方法使用protected进行修饰，故创建其子类
        User user = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<User>(){});
        System.out.println("方式一：" + user);

        //方式二：使用Gson的思想.
        User user1 = JSONObject.parseObject(jsonObject.toJSONString(), User.class);
        System.out.println("方式二："  + user);
    }

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
