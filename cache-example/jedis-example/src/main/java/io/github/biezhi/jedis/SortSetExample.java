package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Redis SortSet 操作
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class SortSetExample {

    public static void main(String[] args) {

        System.out.println("=============SortSet==========================");
        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            jedis.zadd("hackers", 1940, "Alan Kay");
            jedis.zadd("hackers", 1953, "Richard Stallman");
            jedis.zadd("hackers", 1965, "Yukihiro Matsumoto");
            jedis.zadd("hackers", 1916, "Claude Shannon");
            jedis.zadd("hackers", 1969, "Linus Torvalds");
            jedis.zadd("hackers", 1912, "Alan Turing");
            Set<String> setValues = jedis.zrange("hackers", 0, -1);
            System.out.println(setValues);
            Set<String> setValues2 = jedis.zrevrange("hackers", 0, -1);
            System.out.println(setValues2);

            // 清空数据
            System.out.println(jedis.flushDB());
            // 添加数据
            jedis.zadd("zset", 10.1, "hello");
            jedis.zadd("zset", 10.0, ":");
            jedis.zadd("zset", 9.0, "zset");
            jedis.zadd("zset", 11.0, "zset!");
            // 元素个数
            System.out.println(jedis.zcard("zset"));
            // 元素下标
            System.out.println(jedis.zscore("zset", "zset"));
            // 集合子集
            System.out.println(jedis.zrange("zset", 0, -1));
            // 删除元素
            System.out.println(jedis.zrem("zset", "zset!"));
            System.out.println(jedis.zcount("zset", 9.5, 10.5));
            // 整个集合值
            System.out.println(jedis.zrange("zset", 0, -1));
        }
    }

}
