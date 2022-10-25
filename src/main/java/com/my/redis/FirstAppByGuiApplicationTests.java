package com.my.redis;

import com.alibaba.fastjson.JSON;
import com.my.redis.entity.User;
import com.my.redis.manager.IGlobalCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstAppByGuiApplicationTests {
    @Autowired
    private IGlobalCache globalCache;


    @Test
    public void test() {
        globalCache.set("key2", "value3");
        globalCache.lSetAll("list", Arrays.asList("hello", "redis"));
        List<Object> list = globalCache.lGet("list", 0, -1);
        System.out.println(globalCache.get("key2"));
    }

    @Test
    public void test1() {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (int i = 0; i <10000 ; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("key_"+i,"value_"+i);
            globalCache.addZset("data", map, i);
        }

        /* Set<Object> zSetKey = globalCache.getZset("data", 0L, -1L);
        System.out.println(zSetKey.toArray().toString());
        Object[] objects = zSetKey.toArray();
        for (int i = 0; i <objects.length ; i++) {
            System.out.println(objects[i]);
        }*/
    }

    @Test
    public void test4(){
        User user1 = new User();
        user1.setName("miss");
        user1.setId(34);
        String s = JSON.toJSONString(user1);
        System.out.println(s);

        boolean is = globalCache.set("user:1", s);
        //User  o = (User)globalCache.get("user:1");

        //System.out.println(o.toString());

        String user = "{\"name\":\"name1\",\"age\":\"123\"}";
        User user2 = JSON.parseObject(user, User.class);
        System.out.println(user2.toString());

    }

 }
