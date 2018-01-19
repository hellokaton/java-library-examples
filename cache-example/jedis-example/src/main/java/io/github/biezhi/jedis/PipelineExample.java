package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * Redis Pipeline 操作
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class PipelineExample {

    public static void main(String[] args) {

        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            long start = System.currentTimeMillis();
            jedis.flushDB();
            Pipeline p = jedis.pipelined();
            for (int i = 0; i < 10000; i++) {
                p.set("age2" + i, i + "");
                System.out.println(p.get("age2" + i));
            }
            p.sync();// 这段代码获取所有的response

            long end = System.currentTimeMillis();

            System.out.println("use pipeline cost:" + (end - start) + "ms");

        }
    }

}
