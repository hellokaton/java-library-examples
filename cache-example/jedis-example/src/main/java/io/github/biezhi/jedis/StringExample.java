package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

/**
 * Redis String 操作
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class StringExample {

    public static void main(String[] args) {

        System.out.println("====================String==========================");
        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            // String
            jedis.set("key", "Hello World!");
            String value = jedis.get("key");
            System.out.println(value);

            // 清空数据
            System.out.println(jedis.flushDB());

            // 存储数据
            jedis.set("foo", "bar");
            System.out.println(jedis.get("foo"));

            // 若key不存在，则存储
            jedis.setnx("foo", "foo not exits");
            System.out.println(jedis.get("foo"));

            // 覆盖数据
            jedis.set("foo", "foo update");
            System.out.println(jedis.get("foo"));

            // 追加数据
            jedis.append("foo", " hello, world");
            System.out.println(jedis.get("foo"));

            // 设置key的有效期，并存储数据
            jedis.setex("foo", 2, "foo not exits");
            System.out.println(jedis.get("foo"));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            System.out.println(jedis.get("foo"));

            // 获取并更改数据
            jedis.set("foo", "foo update");
            System.out.println(jedis.getSet("foo", "foo modify"));

            // 截取value的值
            System.out.println(jedis.getrange("foo", 1, 3));
            System.out.println(jedis.mset("mset1", "mvalue1", "mset2", "mvalue2",
                    "mset3", "mvalue3", "mset4", "mvalue4"));

            System.out.println(jedis.mget("mset1", "mset2", "mset3", "mset4"));
            System.out.println(jedis.del(new String[] { "foo", "foo1", "foo3" }));
        }
    }

}
