package com.my.redis.saveobject.jsonstirng;

import com.alibaba.fastjson.JSON;
import com.my.redis.entity.User;
import com.my.redis.manager.IGlobalCache;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    private IGlobalCache globalCache;

    @org.junit.Test
    public void saveJsonStringTest(){
        globalCache.set("jsonPojo:user",JSON.toJSONString(new User(2,"lumia")));
        String user = (String)globalCache.get("jsonPojo:user");
        User user1 = JSON.parseObject(user, User.class);
        System.out.println(user1.toString());
     }
 }
