package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Redis Set 操作
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class SetExample {

    public static void main(String[] args) {

        System.out.println("===================Set==========================");
        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            jedis.sadd("myset", "1");
            jedis.sadd("myset", "2");
            jedis.sadd("myset", "3");
            jedis.sadd("myset", "4");
            Set<String> setValues = jedis.smembers("myset");
            System.out.println(setValues);

            // 移除noname
            jedis.srem("myset", "4");
            // 获取所有加入的value
            System.out.println(jedis.smembers("myset"));
            // 判断 minxr
            System.out.println(jedis.sismember("myset", "4"));
            // 是否是sname集合的元素
            // 返回集合的元素个数
            System.out.println(jedis.scard("sname"));


            // 清空数据
            System.out.println(jedis.flushDB());
            // 添加数据
            jedis.sadd("sets", "HashSet");
            jedis.sadd("sets", "SortedSet");
            jedis.sadd("sets", "TreeSet");
            // 判断value是否在列表中
            System.out.println(jedis.sismember("sets", "TreeSet"));
            ;
            // 整个列表值
            System.out.println(jedis.smembers("sets"));
            // 删除指定元素
            System.out.println(jedis.srem("sets", "SortedSet"));
            // 出栈
            System.out.println(jedis.spop("sets"));
            System.out.println(jedis.smembers("sets"));
            //
            jedis.sadd("sets1", "HashSet1");
            jedis.sadd("sets1", "SortedSet1");
            jedis.sadd("sets1", "TreeSet");
            jedis.sadd("sets2", "HashSet2");
            jedis.sadd("sets2", "SortedSet1");
            jedis.sadd("sets2", "TreeSet1");
            // 交集
            System.out.println(jedis.sinter("sets1", "sets2"));
            // 并集
            System.out.println(jedis.sunion("sets1", "sets2"));
            // 差集
            System.out.println(jedis.sdiff("sets1", "sets2"));
        }
    }

}
