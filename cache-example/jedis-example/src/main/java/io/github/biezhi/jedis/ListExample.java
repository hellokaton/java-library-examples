package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Redis List 操作
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class ListExample {

    public static void main(String[] args) {

        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            // 开始前，先移除所有的内容
            jedis.del("messages");
            jedis.rpush("messages", "Hello how are you?");
            jedis.rpush("messages", "Fine thanks. I'm having fun with redis.");
            jedis.rpush("messages", "I should look into this NOSQL thing ASAP");

            // 再取出所有数据jedis.lrange是按范围取出，
            // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
            List<String> values = jedis.lrange("messages", 0, -1);
            System.out.println(values);

            // 清空数据
            System.out.println(jedis.flushDB());
            // 添加数据
            jedis.lpush("lists", "vector");
            jedis.lpush("lists", "ArrayList");
            jedis.lpush("lists", "LinkedList");
            // 数组长度
            System.out.println(jedis.llen("lists"));
            // 排序
            System.out.println(jedis.sort("lists"));
            // 字串
            System.out.println(jedis.lrange("lists", 0, 3));
            // 修改列表中单个值
            jedis.lset("lists", 0, "hello list!");
            // 获取列表指定下标的值
            System.out.println(jedis.lindex("lists", 1));
            // 删除列表指定下标的值
            System.out.println(jedis.lrem("lists", 1, "vector"));
            // 删除区间以外的数据
            System.out.println(jedis.ltrim("lists", 0, 1));
            // 列表出栈
            System.out.println(jedis.lpop("lists"));
            // 整个列表值
            System.out.println(jedis.lrange("lists", 0, -1));

        }
    }

}
