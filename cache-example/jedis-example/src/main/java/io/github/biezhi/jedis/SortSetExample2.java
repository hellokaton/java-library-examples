package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Redis SortSet 操作
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class SortSetExample2 {

    public static void main(String[] args) {

        System.out.println("=============SortSet==========================");
        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            jedis.zadd("zhongsou:hackers", 1940, "Alan Kay");
            jedis.zadd("zhongsou:hackers", 1953, "Richard Stallman");
            jedis.zadd("zhongsou:hackers", 1943, "Jay");
            jedis.zadd("zhongsou:hackers", 1920, "Jellon");
            jedis.zadd("zhongsou:hackers", 1965, "Yukihiro Matsumoto");
            jedis.zadd("zhongsou:hackers", 1916, "Claude Shannon");
            jedis.zadd("zhongsou:hackers", 1969, "Linus Torvalds");
            jedis.zadd("zhongsou:hackers", 1912, "Alan Turing");

            Set<String> hackers = jedis.zrange("zhongsou:hackers", 0, -1);
            System.out.println(hackers);

            Set<String> hackers2 = jedis.zrevrange("zhongsou:hackers", 0, -1);
            System.out.println(hackers2);

            // 区间操作,我们请求Redis返回score介于负无穷到1920年之间的元素（两个极值也包含了）。
            Set<String> hackers3 = jedis.zrangeByScore("zhongsou:hackers", "-inf",
                    "1920");
            System.out.println(hackers3);

            // ZREMRANGEBYSCORE 这个名字虽然不算好，但他却非常有用，还会返回已删除的元素数量。
            long num = jedis.zremrangeByScore("zhongsou:hackers", "-inf", "1920");
            System.out.println(num);

            jedis.flushAll();

        }
    }

}
