package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

/**
 * Redis Key 操作
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class KeyExample {

    public static void main(String[] args) {

        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            System.out.println("=============key==========================");
            // 清空数据
            System.out.println(jedis.flushDB());
            System.out.println(jedis.echo("foo"));
            // 判断key否存在
            System.out.println(jedis.exists("foo"));
            jedis.set("key", "values");
            System.out.println(jedis.exists("key"));
        }
    }

}
