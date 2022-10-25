package com.my.redis;

import com.my.redis.manager.IGlobalCache;
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


    /**
     * redis 缓存分页 zset
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/test/redis",method = RequestMethod.POST)
    public Object test(@RequestBody Page page){
        Set set = globalCache.zSetGetByPage(page.getKey(), page.pageNum, page.getPageSize());
        HashMap<Object, Object> result = new HashMap<>();
        result.put("pageCurrent",page.pageNum);
        result.put("pageSize",page.pageSize);
        Long total = globalCache.countZset(page.getKey());
        result.put("total",total);
        result.put("pagetotal",total/page.pageSize == 0 ? total/page.pageSize : total/page.pageSize + 1);
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
