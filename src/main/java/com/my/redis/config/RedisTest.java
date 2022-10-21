package com.my.redis.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class RedisTest {
    JedisCluster jedisCluster = null;

    static String prefix = "luffi:lbl";
    static String KEY_SPLIT = ":"; //用于隔开缓存前缀与缓存键值

    String nameKey = prefix + KEY_SPLIT + "name";

    /**
     * 因为是测试，这里没有写单例
     */
    @Before
    public void before(){
        String[] serverArray = "101.43.137.147:6379".split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }

        //注意：这里超时时间不要太短，他会有超时重试机制。而且其他像httpclient、dubbo等RPC框架也要注意这点
        jedisCluster = new JedisCluster(nodes, 1000, 1000, 1, "root", new GenericObjectPoolConfig());

//        大多数测试都是使用【nameKey】测试的，所以在启动之前先把这个key删掉
        jedisCluster.del(nameKey);
    }

    /**
     * 发布
     */
    @Test
    public void publish(){
        System.out.println(jedisCluster.publish("channel1", "ss"));
    }
 }
