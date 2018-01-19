package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * [向Redis list压入ID而不是实际的数据]
 * 在上面的例子里 ，我们将“对象”（此例中是简单消息）直接压入Redis list，但通常不应这么做，
 * 由于对象可能被多次引用：例如在一个list中维护其时间顺序，在一个集合中保存它的类别，只要有必要，它还会出现在其他list中，等等。
 * 让我们回到reddit.com的例子，将用户提交的链接（新闻）添加到list中，有更可靠的方法如下所示：
 * $ redis-cli incr next.news.id
 * (integer) 1
 * $ redis-cli set news:1:title "Redis is simple"
 * OK
 * $ redis-cli set news:1:url "http://code.google.com/p/redis"
 * OK
 * $ redis-cli lpush submitted.news 1
 * OK
 * 我们自增一个key，很容易得到一个独一无二的自增ID，然后通过此ID创建对象–为对象的每个字段设置一个key。最后将新对象的ID压入submitted.news list。
 * 这只是牛刀小试。在命令参考文档中可以读到所有和list有关的命令。你可以删除元素，旋转list，根据索引获取和设置元素，当然也可以用LLEN得到list的长度。
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class ListStrExample {

    public static void main(String[] args) {

        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            String title = "王爵nice";
            String url   = "https://github.com/biezhi";

            long adInfoId = jedis.incr("ad:adinfo:next.id");
            jedis.set("ad:adinfo:" + adInfoId + ":title", title);
            jedis.set("ad:adinfo:" + adInfoId + ":url", url);
            jedis.lpush("ad:adinfo", String.valueOf(adInfoId));

            String       resultTitle = jedis.get("ad:adinfo:" + adInfoId + ":title");
            String       resultUrl   = jedis.get("ad:adinfo:" + adInfoId + ":url");
            List<String> ids         = jedis.lrange("ad:adinfo", 0, -1);
            System.out.println(resultTitle);
            System.out.println(resultUrl);
            System.out.println(ids);

            /**
             * dbsize返回的是所有key的数目，包括已经过期的， 而redis-cli keys "*"查询得到的是有效的key数目
             */
            System.out.println(jedis.dbSize());
            jedis.flushAll();
        }
    }

}
