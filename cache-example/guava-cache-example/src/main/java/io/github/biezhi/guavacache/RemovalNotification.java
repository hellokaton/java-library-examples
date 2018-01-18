package io.github.biezhi.guavacache;

import com.google.common.cache.*;

/**
 * 移除监听
 * <p>
 * 通过CacheBuilder.removalListener(RemovalListener)，你可以声明一个监听器，以便缓存项被移除时做一些额外操作。
 * 缓存项被移除时，RemovalListener会获取移除通知[RemovalNotification]，其中包含移除原因[RemovalCause]、键和值。
 *
 * @author biezhi
 * @date 2018/1/18
 */
public class RemovalNotification {

    public static void main(String[] args) {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return "找不到: " + key;
            }
        };

        RemovalListener<String, String> listener;
        listener = n -> {
            if (n.wasEvicted()) {
                String cause = n.getCause().name();
                System.out.println(RemovalCause.SIZE.toString().equals(cause));
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(listener)
                .build(loader);

        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("last");

        System.out.println("Cache Size: " + cache.size());
    }

}
