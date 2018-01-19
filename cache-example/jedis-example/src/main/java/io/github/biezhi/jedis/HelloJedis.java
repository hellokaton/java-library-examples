package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

/**
 * 第一个 Redis 操作示例
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class HelloJedis {

    public static void main(String[] args) {
        // 连接到本地 localhost:6379 的redis服务
        Jedis jedis = new Jedis("localhost", 6379);

        // 设置一个 Key
        jedis.set("name", "biezhi");

        // 获取key的数据
        System.out.println(jedis.get("name"));
    }

}
