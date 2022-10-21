package com.my.redis.config;

import lombok.Getter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

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

 }
