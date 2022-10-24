package com.my.redis.config;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Page {
    String key;
    int pageNum;
    int pageSize;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    @Test
    public void publish(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("key","value");
        String mapJson = JSON.toJSONString(map);
        System.out.println(map.toString());
        String aa = map.toString();
        String s = JSON.toJSONString("{\"key\":\"value\"}");
        Map map1 = JSON.parseObject("{\"key\":\"value\"}", Map.class);
        System.out.println(s);
        System.out.println(map1);

    }

    @Test
    public void test1(){
        User1 user1 = new User1();
        user1.setName("miss");
        user1.setAge("34");
        String s = JSON.toJSONString(user1);
        System.out.println(s);
    }

    class User1{
        String name;
        String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }
}
