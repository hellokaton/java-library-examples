package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

/**
 * 基础的Redis操作
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class BasicExample {

    public static void main(String[] args) {

        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            // 向 key --> name 中放入了 value --> minxr
            jedis.set("name", "minxr");
            String ss = jedis.get("name");
            System.out.println(ss);

            // 很直观，类似map 将 biezhi append到已经有的value之后
            jedis.append("name", "biezhi");
            ss = jedis.get("name");
            System.out.println(ss);

            // 2、直接覆盖原来的数据
            jedis.set("name", "biezhi");
            System.out.println(jedis.get("biezhi"));

            // 删除key对应的记录
            jedis.del("name");
            // 执行结果：null
            System.out.println(jedis.get("name"));

            /**
             * mset相当于 jedis.set("name","minxr"); jedis.set("jarorwar","aaa");
             */
            jedis.mset("name", "minxr", "jarorwar", "aaa");
            System.out.println(jedis.mget("name", "jarorwar"));
        }
    }

}
