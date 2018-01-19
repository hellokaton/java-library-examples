package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Redis mget 操作
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class MGetExample {

    public static void main(String[] args) {

        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            jedis.rpush("ids", "aa");
            jedis.rpush("ids", "bb");
            jedis.rpush("ids", "cc");

            List<String> ids = jedis.lrange("ids", 0, -1);

            jedis.set("aa", "{'name':'zhoujie','age':20}");
            jedis.set("bb", "{'name':'yilin','age':28}");
            jedis.set("cc", "{'name':'lucy','age':21}");
            List<String> list = jedis.mget(ids.toArray(new String[ids.size()]));
            System.out.println(list);
        }
    }

}
