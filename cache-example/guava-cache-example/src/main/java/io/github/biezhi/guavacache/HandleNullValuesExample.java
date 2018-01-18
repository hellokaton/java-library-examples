package io.github.biezhi.guavacache;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * 处理空值
 *
 * 实际上Guava整体设计思想就是拒绝null的，很多地方都会执行 com.google.common.base.Preconditions.checkNotNull 的检查。
 *
 * @author biezhi
 * @date 2018/1/18
 */
public class HandleNullValuesExample {

    public static void main(String[] args) {
        CacheLoader<String, Optional<String>> loader;
        loader = new CacheLoader<String, Optional<String>>() {
            @Override
            public Optional<String> load(String key) {
                return Optional.fromNullable(getSuffix(key));
            }
        };
        LoadingCache<String, Optional<String>> cache;
        cache = CacheBuilder.newBuilder().build(loader);

        System.out.println("txt: " + cache.getUnchecked("text.txt").get());
        System.out.println("hello: " + cache.getUnchecked("hello").isPresent());
    }

    private static String getSuffix(final String str) {
        int lastIndex = str.lastIndexOf('.');
        if (lastIndex == -1) {
            return null;
        }
        return str.substring(lastIndex + 1);
    }

}
