package io.github.biezhi.guavacache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.TimeUnit;

/**
 * 刷新缓存
 * <p>
 * 刷新和回收不太一样。正如LoadingCache.refresh(K)所声明，刷新表示为键加载新值，这个过程可以是异步的。
 * 在刷新操作进行时，缓存仍然可以向其他线程返回旧值，而不像回收操作，读缓存的线程必须等待新值加载完成。
 *
 * @author biezhi
 * @date 2018/1/18
 */
public class RefreshCacheExample {

    public static void main(String[] args) {

        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return key.toUpperCase();
            }

            /**
             * 重写reload方法可以定制自己的reload策略
             * @param key
             * @param oldValue
             * @return
             * @throws Exception
             */
            @Override
            public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                return super.reload(key, oldValue);
            }
        };

        LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(loader);

        cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                .build(loader);

        /**
         * CacheBuilder.refreshAfterWrite(long, TimeUnit)可以为缓存增加自动定时刷新功能。
         * 和expireAfterWrite相反，refreshAfterWrite通过定时刷新可以让缓存项保持可用，
         * 但请注意：缓存项只有在被检索时才会真正刷新（如果CacheLoader.refresh实现为异步，那么检索不会被刷新拖慢）。
         * 因此，如果你在缓存上同时声明expireAfterWrite和refreshAfterWrite，缓存并不会因为刷新盲目地定时重置，
         * 如果缓存项没有被检索，那刷新就不会真的发生，缓存项在过期时间后也变得可以回收。
         */
    }

}
