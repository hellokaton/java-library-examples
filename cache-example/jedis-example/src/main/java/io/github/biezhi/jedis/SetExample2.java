package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 下面是一个简单的方案：对每个想加标签的对象，用一个标签ID集合与之关联，并且对每个已有的标签，一组对象ID与之关联。 例如假设我们的新闻ID
 * 1000被加了三个标签tag 1,2,5和77，就可以设置下面两个集合： $ redis-cli sadd news:1000:tags 1
 * (integer) 1 $ redis-cli sadd news:1000:tags 2 (integer) 1 $ redis-cli
 * sadd news:1000:tags 5 (integer) 1 $ redis-cli sadd news:1000:tags 77
 * (integer) 1 $ redis-cli sadd tag:1:objects 1000 (integer) 1 $ redis-cli
 * sadd tag:2:objects 1000 (integer) 1 $ redis-cli sadd tag:5:objects 1000
 * (integer) 1 $ redis-cli sadd tag:77:objects 1000 (integer) 1
 * 要获取一个对象的所有标签，如此简单： $ redis-cli smembers news:1000:tags 1. 5 2. 1 3. 77 4.
 * 2 而有些看上去并不简单的操作仍然能使用相应的Redis命令轻松实现。例如我们也许想获得一份同时拥有标签1, 2,
 * 10和27的对象列表。这可以用SINTER命令来做，他可以在不同集合之间取出交集。因此为达目的我们只需： $ redis-cli sinter
 * tag:1:objects tag:2:objects tag:10:objects tag:27:objects ... no result
 * in our dataset composed of just one object ...
 * 在命令参考文档中可以找到和集合相关的其他命令，令人感兴趣的一抓一大把。一定要留意SORT命令，Redis集合和list都是可排序的。
 */
public class SetExample2 {

    public static void main(String[] args) {

        System.out.println("===================Set==========================");
        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            jedis.sadd("biezhi:news:1000:tags", "1");
            jedis.sadd("biezhi:news:1000:tags", "2");
            jedis.sadd("biezhi:news:1000:tags", "5");
            jedis.sadd("biezhi:news:1000:tags", "77");
            jedis.sadd("biezhi:news:2000:tags", "1");
            jedis.sadd("biezhi:news:2000:tags", "2");
            jedis.sadd("biezhi:news:2000:tags", "5");
            jedis.sadd("biezhi:news:2000:tags", "77");
            jedis.sadd("biezhi:news:3000:tags", "2");
            jedis.sadd("biezhi:news:4000:tags", "77");
            jedis.sadd("biezhi:news:5000:tags", "1");
            jedis.sadd("biezhi:news:6000:tags", "5");

            jedis.sadd("biezhi:tag:1:objects", 1000 + "");
            jedis.sadd("biezhi:tag:2:objects", 1000 + "");
            jedis.sadd("biezhi:tag:5:objects", 1000 + "");
            jedis.sadd("biezhi:tag:77:objects", 1000 + "");

            jedis.sadd("biezhi:tag:1:objects", 2000 + "");
            jedis.sadd("biezhi:tag:2:objects", 2000 + "");
            jedis.sadd("biezhi:tag:5:objects", 2000 + "");
            jedis.sadd("biezhi:tag:77:objects", 2000 + "");

            Set<String> sets = jedis.sinter("biezhi:tag:1:objects",
                    "biezhi:tag:2:objects", "biezhi:tag:5:objects",
                    "biezhi:tag:77:objects");
            System.out.println(sets);
            jedis.flushAll();
        }
    }

}
