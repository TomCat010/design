package com.my.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Set;

@Controller
public class TestController {
    @Autowired
    private IGlobalCache globalCache;


    @ResponseBody
    @RequestMapping(value = "/test/redis",method = RequestMethod.POST)
    public Object test(@RequestBody Page page){
        Set set = globalCache.zSetGetByPage(page.getKey(), page.pageNum, page.getPageSize());
        HashMap<Object, Object> result = new HashMap<>();
        result.put("page",page.pageNum);
        result.put("pageSize",page.pageSize);
        result.put("total",globalCache.countZset(page.getKey()));
        result.put("data",set);
        System.out.println(set);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/test/redis1")
    public Object test1( ){
         return "success";
    }
}
