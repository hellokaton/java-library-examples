package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Redis Hash 操作
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class HashExample {

    public static void main(String[] args) {

        System.out.println("======================Hash==========================");

        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            Map<String, String> pairs = new HashMap<>();
            pairs.put("name", "Akshi");
            pairs.put("age", "2");
            pairs.put("sex", "Female");
            jedis.hmset("kid", pairs);
            // 结果是个泛型的LIST
            List<String> name = jedis.hmget("kid", "name");
            //删除map中的某个键值
            // jedis.hdel("kid","age");
            // 因为删除了，所以返回的是null
            System.out.println(jedis.hmget("kid", "pwd"));
            // 返回key为user的键中存放的值的个数
            System.out.println(jedis.hlen("kid"));
            // 是否存在key为user的记录
            System.out.println(jedis.exists("kid"));
            // 返回map对象中的所有key
            System.out.println(jedis.hkeys("kid"));
            // 返回map对象中的所有value
            System.out.println(jedis.hvals("kid"));

            Iterator<String> iter = jedis.hkeys("kid").iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                System.out.println(key + ":" + jedis.hmget("kid", key));
            }

            List<String> values = jedis.hmget("kid", new String[]{"name", "age", "sex"});
            System.out.println(values);
            Set<String> setValues = jedis.hkeys("kid");
            System.out.println(setValues);
            values = jedis.hvals("kid");
            System.out.println(values);
            pairs = jedis.hgetAll("kid");
            System.out.println(pairs);


            // 清空数据
            System.out.println(jedis.flushDB());
            // 添加数据
            jedis.hset("hashs", "entryKey", "entryValue");
            jedis.hset("hashs", "entryKey1", "entryValue1");
            jedis.hset("hashs", "entryKey2", "entryValue2");
            // 判断某个值是否存在
            System.out.println(jedis.hexists("hashs", "entryKey"));
            // 获取指定的值
            System.out.println(jedis.hget("hashs", "entryKey")); // 批量获取指定的值
            System.out.println(jedis.hmget("hashs", "entryKey", "entryKey1"));
            // 删除指定的值
            System.out.println(jedis.hdel("hashs", "entryKey"));
            // 为key中的域 field 的值加上增量 increment
            System.out.println(jedis.hincrBy("hashs", "entryKey", 123l));
            // 获取所有的keys
            System.out.println(jedis.hkeys("hashs"));
            // 获取所有的values
            System.out.println(jedis.hvals("hashs"));
        }
    }

}
