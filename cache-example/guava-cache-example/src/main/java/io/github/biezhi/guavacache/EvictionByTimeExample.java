package io.github.biezhi.guavacache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;

import java.util.concurrent.TimeUnit;

/**
 * 定时回收
 * <p>
 * 除了通过size来回收记录，我们也可以选择定时回收
 * <p>
 * CacheBuilder提供两种定时回收的方法：
 * <p>
 * 1. expireAfterAccess(long, TimeUnit)：缓存项在给定时间内没有被读/写访问，则回收。请注意这种缓存的回收顺序和基于大小回收一样。
 * 2. expireAfterWrite(long, TimeUnit)：缓存项在给定时间内没有被写访问（创建或覆盖），则回收。如果认为缓存数据总是在固定时候后变得陈旧不可用，这种回收方式是可取的。
 *
 * @author biezhi
 * @date 2018/1/18
 */
public class EvictionByTimeExample {

    public static void main(String[] args) throws InterruptedException {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return "找不到: " + key;
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder()
                .expireAfterAccess(2, TimeUnit.MILLISECONDS)
                .build(loader);

        cache.getUnchecked("hello");

        System.out.println("Cache Size: " + cache.size());

        cache.getUnchecked("hello");

        Thread.sleep(300);

        cache.getUnchecked("test");

        System.out.println("Cache Size: " + cache.size());
        System.out.println(cache.getIfPresent("hello"));

    }

}
