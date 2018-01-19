package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

/**
 * Redis DB 操作
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class DBExample {

    public static void main(String[] args) {

        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            // select db-index
            System.out.println(jedis.select(0));
            // 通过索引选择数据库，默认连接的数据库所有是0,默认数据库数是16个。返回1表示成功，0失败
            // dbsize 返回当前数据库的key数量
            System.out.println(jedis.dbSize());
            // 返回匹配指定模式的所有key
            System.out.println(jedis.keys("*"));
            System.out.println(jedis.randomKey());
            // 删除当前数据库中所有key,此方法不会失败。慎用
            jedis.flushDB();
            // 删除所有数据库中的所有key，此方法不会失败。更加慎用
            jedis.flushAll();
        }
    }

}
