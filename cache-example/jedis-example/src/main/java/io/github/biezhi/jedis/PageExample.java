package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Redis 分页 操作
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class PageExample {

    public static void main(String[] args) {

        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            int pageNo   = 6;
            int pageSize = 6;
            jedis.del("a");
            for (int i = 1; i <= 30; i++) {
                jedis.rpush("a", i + "");
            }

            // 因为redis中list元素位置基数是0
            int start = pageSize * (pageNo - 1);
            int end   = start + pageSize - 1;

            // 从start算起，start算一个元素，到结束那个元素
            List<String> results = jedis.lrange("a", start, end);
            for (String str : results) {
                System.out.println(str);
            }
        }
    }

}
