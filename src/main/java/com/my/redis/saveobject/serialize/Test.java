package com.my.redis.saveobject.serialize;

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
    public void saveSerializeObjectTest(){
        globalCache.set("serializeObject", SerializeUtil.serialize(new User(2,"lumia")));
        Object serializeObject = globalCache.get("serializeObject");
        System.out.println(serializeObject);
        User user = (User)SerializeUtil.unserialize((byte[] )serializeObject);
        System.out.println(user.getId());
     }
    /*rO0ABXNyAC1jb20ubXkucmVkaXMuY29uZmlnLnNhdmVvYmplY3Qu
     c2VyaWFsaXplLlVzZXLTcKU1YVnNRwIAAkkAAmlkTAAEbmFtZXQAEk
     xqYXZhL2xhbmcvU3RyaW5nO3hwAAAAAnQABWx1bWlh*/
}
