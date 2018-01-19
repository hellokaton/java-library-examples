package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

/**
 * Redis 其他操作
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class OtherExample {

    public static void main(String[] args) {

        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            // keys中传入的可以用通配符
            // 返回当前库中所有的key [sose, sanme,
            System.out.println(jedis.keys("*"));
            // name, jarorwar, foo,
            // sname, java framework,
            // user, braand]

            // 返回的sname [sname, name]
            System.out.println(jedis.keys("*name"));
            // 删除key为sanmdde的对象 删除成功返回1
            System.out.println(jedis.del("sanmdde"));
            // 删除失败（或者不存在）返回 0
            // 返回给定key的有效时间，如果是-1则表示永远有效
            System.out.println(jedis.ttl("sname"));
            // 通过此方法，可以指定key的存活（有效时间） 时间为秒
            jedis.setex("timekey", 10, "min");
            try {
                // 睡眠5秒后，剩余时间将为<=5
                Thread.sleep(5000);
            } catch (Exception e) {
            }
            // 输出结果为5
            System.out.println(jedis.ttl("timekey"));
            // 设为1后，下面再看剩余时间就是1了
            jedis.setex("timekey", 1, "min");
            // 输出结果为1
            System.out.println(jedis.ttl("timekey"));
            // 检查key是否存在
            System.out.println(jedis.exists("key"));
            System.out.println(jedis.rename("timekey", "time"));
            // 因为移除，返回为null
            System.out.println(jedis.get("timekey"));
            // 因为将timekey 重命名为time
            System.out.println(jedis.get("time"));
            // 所以可以取得值 min
            // jedis 排序
            // 注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
            // 先清除数据，再加入数据进行测试
            jedis.del("a");
            jedis.rpush("a", "1");
            jedis.lpush("a", "6");
            jedis.lpush("a", "3");
            jedis.lpush("a", "9");
            // [9, 3, 6, 1]
            System.out.println(jedis.lrange("a", 0, -1));
            //输入排序后结果
            // [1, 3, 6, 9]
            System.out.println(jedis.sort("a"));
            System.out.println(jedis.lrange("a", 0, -1));
        }
    }

}
