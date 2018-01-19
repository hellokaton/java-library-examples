package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Jedis Pool 示例
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class JedisPoolExample {

    public static void main(String[] args) {
        /// Jedis implements Closeable. Hence, the jedis instance will be auto-closed after the last statement.
        try (Jedis jedis = JedisUtil.getInstance().getJedis("127.0.0.1", 6379)) {
            /// ... do stuff here ... for example
            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
        }
    }
}
