package com.my.redis.config;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

public class Cluster {
    /*private static volatile JedisCluster cluster;
    static String prefix = "luffi:lbl";
    static String KEY_SPLIT = ":"; //用于隔开缓存前缀与缓存键值

    String nameKey = prefix + KEY_SPLIT + "name";
     static {
        Set<HostAndPort> nodes = new LinkedHashSet<>();
        nodes.add(new HostAndPort("101.43.137.147", 6379));
        cluster = new JedisCluster(nodes,5000,3000,10,"root", new JedisPoolConfig());
    }

    public static void main(String[] args) {
        String keyA = "{"+prefix + KEY_SPLIT + "sortedSet}a";
        String keyB = "{"+prefix + KEY_SPLIT + "sortedSet}b";
        String keyC = "{"+prefix + KEY_SPLIT + "sortedSet}c";

        cluster.del(keyA);
        cluster.del(keyB);

        System.out.println(cluster.zadd(keyA, 10, "aa"));
        Map<String, Double> map = new HashMap<>();
        map.put("b", 8.0);
        map.put("c", 4.0);
        map.put("d", 6.0);
        System.out.println(cluster.zadd(keyA, map));
        System.out.println(cluster.zcard(keyA));//返回有序集 key 的数量。
        System.out.println(cluster.zcount(keyA, 3, 8));//返回有序集 key 中score某个范围的数量。
        System.out.println("zrange: "+cluster.zrange(keyA, 0, -1));//返回有序集 key 中，指定区间内的成员。按score从小到大
        System.out.println("zrevrange: "+cluster.zrevrange(keyA, 0, -1));//返回有序集 key 中，指定区间内的成员。按score从大到小
        System.out.println("zrangeWithScores: "+cluster.zrangeWithScores(keyA, 0, -1));//返回有序集 key 中，指定区间内的成员。按score从小到大.包含分值

        System.out.println("zscore: "+cluster.zscore(keyA, "aa"));
     }*/
}
