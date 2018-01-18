package io.github.biezhi.guavacache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * 简答的 Cache 例子
 *
 * @author biezhi
 * @date 2018/1/18
 */
public class HelloGuavaCache {

    public static void main(String[] args) throws InterruptedException {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            // 当guava cache中不存在，则会调用load方法
            @Override
            public String load(String key) {
                return "找不到数据:" + key;
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder
                .newBuilder()
                // 写数据1s后重新加载缓存
                .refreshAfterWrite(1L, TimeUnit.SECONDS)
                .build(loader);

        System.out.println("Cache Size: " + cache.size());

        cache.put("test", "test");

        System.out.println(cache.getUnchecked("test"));
        System.out.println(cache.getUnchecked("hello"));
        System.out.println("Cache Size: " + cache.size());

        TimeUnit.SECONDS.sleep(2);

        System.out.println(cache.getUnchecked("test"));

    }
}
