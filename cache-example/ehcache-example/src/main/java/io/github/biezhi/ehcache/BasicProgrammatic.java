package io.github.biezhi.ehcache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;

import static org.ehcache.config.builders.CacheConfigurationBuilder.newCacheConfigurationBuilder;
import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManagerBuilder;
import static org.ehcache.config.builders.ResourcePoolsBuilder.heap;
import static org.ehcache.config.units.MemoryUnit.MB;

/**
 * 编码方式创建缓存管理器
 */
@Slf4j
public class BasicProgrammatic {

    public static void main(String[] args) {
        log.info("编码方式创建 CacheManager");

        try (CacheManager cacheManager = newCacheManagerBuilder()
                .withCache("basicCache",
                        newCacheConfigurationBuilder(Long.class, String.class, heap(100).offheap(1, MB)))
                .build(true)) {

            Cache<Long, String> basicCache = cacheManager.getCache("basicCache", Long.class, String.class);

            log.info("设置缓存");
            basicCache.put(1L, "王爵nice");
            String value = basicCache.get(1L);
            log.info("读取缓存: {}", value);
            log.info("关闭 CacheManager");
        }
        log.info("退出程序");
    }
}