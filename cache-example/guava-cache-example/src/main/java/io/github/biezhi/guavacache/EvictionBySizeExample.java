package io.github.biezhi.guavacache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;

/**
 * 基于容量的回收
 * <p>
 * 可以通过maximumSize()方法限制cache的size，如果cache达到了最大限制，oldest items 将会被回收。
 *
 * @author biezhi
 * @date 2018/1/18
 */
public class EvictionBySizeExample {

    public static void main(String[] args) {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return "找不到: " + key;
            }
        };
        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().maximumSize(3).build(loader);
        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("forth");

        System.out.println("Cache Size: " + cache.size());
        System.out.println(cache.getIfPresent("first"));
        System.out.println(cache.getIfPresent("forth"));

        System.out.println("\r\n=============邪恶的分割线=============\r\n");
        //////////////// 定制weight /////////////////
        Weigher<String, String> weighByLength;
        weighByLength = (key, value) -> value.length();
        cache = CacheBuilder.newBuilder()
                .maximumWeight(16)
                .weigher(weighByLength)
                .build(loader);
        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("last");

        System.out.println("Cache Size: " + cache.size());
        System.out.println(cache.getIfPresent("first"));
        System.out.println(cache.getIfPresent("last"));

    }

}
