package io.github.biezhi.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import java.util.List;

/**
 * Redis 排序操作
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class SortExample {

    /**
     * 时间复杂度：
     * O(N+M*log(M))， N 为要排序的列表或集合内的元素数量， M 为要返回的元素数量。
     * 如果只是使用 SORT 命令的 GET 选项获取数据而没有进行排序，时间复杂度 O(N)。
     */
    private static void sort1() {
        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            // 一般SORT用法 最简单的SORT使用方法是SORT key。
            jedis.lpush("mylist", "1");
            jedis.lpush("mylist", "4");
            jedis.lpush("mylist", "6");
            jedis.lpush("mylist", "3");
            jedis.lpush("mylist", "0");
            // List<String> list = redis.sort("sort");// 默认是升序
            SortingParams sortingParameters = new SortingParams();
            sortingParameters.desc();
            // sortingParameters.alpha();//当数据集中保存的是字符串值时，你可以用 ALPHA
            // 修饰符(modifier)进行排序。
            // 可用于分页查询
            sortingParameters.limit(0, 2);
            // 默认是升序
            List<String> list = jedis.sort("mylist", sortingParameters);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
            jedis.flushDB();
        }
    }

    /**
     * LIST结合hash的排序
     */
    private static void sort2() {
        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            jedis.del("user:66", "user:55", "user:33", "user:22", "user:11",
                    "userlist");
            jedis.lpush("userlist", "33");
            jedis.lpush("userlist", "22");
            jedis.lpush("userlist", "55");
            jedis.lpush("userlist", "11");

            jedis.hset("user:66", "name", "66");
            jedis.hset("user:55", "name", "55");
            jedis.hset("user:33", "name", "33");
            jedis.hset("user:22", "name", "79");
            jedis.hset("user:11", "name", "24");
            jedis.hset("user:11", "add", "beijing");
            jedis.hset("user:22", "add", "shanghai");
            jedis.hset("user:33", "add", "guangzhou");
            jedis.hset("user:55", "add", "chongqing");
            jedis.hset("user:66", "add", "xi'an");

            SortingParams sortingParameters = new SortingParams();
            // 符号 "->" 用于分割哈希表的键名(key name)和索引域(hash field)，格式为 "key->field" 。
            sortingParameters.get("user:*->name");
            sortingParameters.get("user:*->add");
            // sortingParameters.by("user:*->name");
            // sortingParameters.get("#");
            List<String> result = jedis.sort("userlist", sortingParameters);
            for (String item : result) {
                System.out.println("item...." + item);
            }
            /**
             * 对应的redis客户端命令是：sort ml get user*->name sort ml get user:*->name get
             * user:*->add
             */
            jedis.flushDB();
        }
    }

    /**
     * SET结合String的排序
     */
    private static void sort3() {
        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            jedis.del("tom:friend:list", "score:uid:123", "score:uid:456",
                    "score:uid:789", "score:uid:101", "uid:123", "uid:456",
                    "uid:789", "uid:101");

            // tom的好友列表
            jedis.sadd("tom:friend:list", "123");
            jedis.sadd("tom:friend:list", "456");
            jedis.sadd("tom:friend:list", "789");
            jedis.sadd("tom:friend:list", "101");

            // 好友对应的成绩
            jedis.set("score:uid:123", "1000");
            jedis.set("score:uid:456", "6000");
            jedis.set("score:uid:789", "100");
            jedis.set("score:uid:101", "5999");

            // 好友的详细信息
            jedis.set("uid:123", "{'uid':123,'name':'lucy'}");
            jedis.set("uid:456", "{'uid':456,'name':'jack'}");
            jedis.set("uid:789", "{'uid':789,'name':'jay'}");
            jedis.set("uid:101", "{'uid':101,'name':'jolin'}");

            SortingParams sortingParameters = new SortingParams();

            sortingParameters.desc();
            // sortingParameters.limit(0, 2);
            // 注意GET操作是有序的，GET user_name_* GET user_password_*
            // 和 GET user_password_* GET user_name_*返回的结果位置不同
            // GET 还有一个特殊的规则—— "GET #"
            sortingParameters.get("#");
            // ，用于获取被排序对象(我们这里的例子是 user_id )的当前元素。
            sortingParameters.get("uid:*");
            sortingParameters.get("score:uid:*");
            sortingParameters.by("score:uid:*");
            // 对应的redis 命令是./redis-cli sort tom:friend:list by score:uid:* get # get
            // uid:* get score:uid:*
            List<String> result = jedis.sort("tom:friend:list", sortingParameters);
            for (String item : result) {
                System.out.println("item..." + item);
            }
        }
    }

    /**
     *
     * 只获取对象而不排序 BY 修饰符可以将一个不存在的 key 当作权重，让 SORT 跳过排序操作。
     * 该方法用于你希望获取外部对象而又不希望引起排序开销时使用。 # 确保fake_key不存在 redis> EXISTS fake_key
     * (integer) 0 # 以fake_key作BY参数，不排序，只GET name 和 GET password redis> SORT
     * user_id BY fake_key GET # GET user_name_* GET user_password_* 1) "222" #
     * id 2) "hacker" # user_name 3) "hey,im in" # password 4) "59230" 5) "jack"
     * 6) "jack201022" 7) "2" 8) "huangz" 9) "nobodyknows" 10) "1" 11) "admin"
     * 12) "a_long_long_password"
     */
    public static void sort4() {
    }

    /**
     *
     保存排序结果 默认情况下， SORT 操作只是简单地返回排序结果，如果你希望保存排序结果，可以给 STORE 选项指定一个 key
     * 作为参数，排序结果将以列表的形式被保存到这个 key 上。(若指定 key 已存在，则覆盖。) redis> EXISTS
     * user_info_sorted_by_level # 确保指定key不存在 (integer) 0 redis> SORT user_id BY
     * user_level_* GET # GET user_name_* GET user_password_* STORE
     * user_info_sorted_by_level # 排序 (integer) 12 # 显示有12条结果被保存了 redis> LRANGE
     * user_info_sorted_by_level 0 11 # 查看排序结果 1) "59230" 2) "jack" 3)
     * "jack201022" 4) "2" 5) "huangz" 6) "nobodyknows" 7) "222" 8) "hacker" 9)
     * "hey,im in" 10) "1" 11) "admin" 12) "a_long_long_password" 一个有趣的用法是将 SORT
     * 结果保存，用 EXPIRE 为结果集设置生存时间，这样结果集就成了 SORT 操作的一个缓存。 这样就不必频繁地调用 SORT
     * 操作了，只有当结果集过期时，才需要再调用一次 SORT 操作。
     * 有时候为了正确实现这一用法，你可能需要加锁以避免多个客户端同时进行缓存重建(也就是多个客户端，同一时间进行 SORT
     * 操作，并保存为结果集)，具体参见 SETNX 命令。
     */
    private static void sort5() {
        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            // 一般SORT用法 最简单的SORT使用方法是SORT key。
            jedis.lpush("mylist", "1");
            jedis.lpush("mylist", "4");
            jedis.lpush("mylist", "6");
            jedis.lpush("mylist", "3");
            jedis.lpush("mylist", "0");
            // List<String> list = redis.sort("sort");// 默认是升序
            SortingParams sortingParameters = new SortingParams();
            sortingParameters.desc();
            // sortingParameters.alpha();//当数据集中保存的是字符串值时，你可以用 ALPHA
            // 修饰符(modifier)进行排序。
            // sortingParameters.limit(0, 2);//可用于分页查询

            // 没有使用 STORE 参数，返回列表形式的排序结果. 使用 STORE 参数，返回排序结果的元素数量。

            // 排序后指定排序结果到一个KEY中，这里讲结果覆盖原来的KEY
            jedis.sort("mylist", sortingParameters, "mylist");

            List<String> list = jedis.lrange("mylist", 0, -1);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }

            // tom的好友列表
            jedis.sadd("tom:friend:list", "123");
            jedis.sadd("tom:friend:list", "456");
            jedis.sadd("tom:friend:list", "789");
            jedis.sadd("tom:friend:list", "101");

            // 好友对应的成绩
            jedis.set("score:uid:123", "1000");
            jedis.set("score:uid:456", "6000");
            jedis.set("score:uid:789", "100");
            jedis.set("score:uid:101", "5999");

            // 好友的详细信息
            jedis.set("uid:123", "{'uid':123,'name':'lucy'}");
            jedis.set("uid:456", "{'uid':456,'name':'jack'}");
            jedis.set("uid:789", "{'uid':789,'name':'jay'}");
            jedis.set("uid:101", "{'uid':101,'name':'jolin'}");

            sortingParameters = new SortingParams();

            // sortingParameters.desc();
            // GET 还有一个特殊的规则—— "GET #"
            sortingParameters.get("#");
            // ，用于获取被排序对象(我们这里的例子是 user_id )的当前元素。
            sortingParameters.by("score:uid:*");
            jedis.sort("tom:friend:list", sortingParameters, "tom:friend:list");
            List<String> result = jedis.lrange("tom:friend:list", 0, -1);
            for (String item : result) {
                System.out.println("item..." + item);
            }

            jedis.flushDB();
        }
    }

    private static void more(){
        //ZRANGE取出最新的10个项目。
        //使用LPUSH + LTRIM，确保只取出最新的1000条项目。
        //HINCRBY key field increment,为哈希表 key 中的域 field 的值加上增量 increment
        //INCRBY,HINCRBY等等，redis有了原子递增（atomic increment），你可以放心的加上各种计数，用GETSET重置，或者是让它们过期。
        // LREM greet 2 morning     # 移除从表头到表尾，最先发现的两个 morning,这个可以用来删除特定评论
        // zrevrank test a 查看a在sorted set中倒排序时排在第几名，查询结果按照INDEX，所以INDEX是3表示排在第四名
        // zrank test a 相反，表示正排序时候的名次
        // zscore test one表示one这个元素在sorted set中的score为多少
        // zrevrange test 0 -1 表示sorted set倒排序,zrange test 0 -1表示正排序
        //将一个或多个 member 元素及其 score 值加入到有序集 key 当中。如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。
        //zrem test one删除sorted set中某个元素
    }

    public List<String> get_latest_comments(int start, int num_items){
        //获取最新评论
        //LPUSH latest.comments <ID>
        //-我们将列表裁剪为指定长度，因此Redis只需要保存最新的5000条评论：
        //LTRIM latest.comments 0 5000
        //们做了限制不能超过5000个ID，因此我们的获取ID函数会一直询问Redis。只有在start/count参数超出了这个范围的时候，才需要去访问数据库。
        Jedis jedis = JedisUtil.getInstance().getJedis();
        List<String> id_list = jedis.lrange("latest.comments",start,start+num_items-1) ;

        if(id_list.size()<num_items){
            //id_list = SQL.EXECUTE("SELECT ... ORDER BY time LIMIT ...");
        }
        return id_list;
    }

    public static void main(String[] args) {
        sort1();
        sort2();
        sort3();
    }

}
