package io.github.biezhi.ehcache;

import java.net.URI;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;

import static java.net.URI.create;
import static org.ehcache.clustered.client.config.builders.ClusteredResourcePoolBuilder.clusteredDedicated;
import static org.ehcache.clustered.client.config.builders.ClusteringServiceConfigurationBuilder.cluster;
import static org.ehcache.config.builders.CacheConfigurationBuilder.newCacheConfigurationBuilder;
import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManagerBuilder;
import static org.ehcache.config.builders.ResourcePoolsBuilder.heap;
import static org.ehcache.config.units.MemoryUnit.MB;

/**
 * 编码方式创建集群 CacheManager
 */
@Slf4j
public class ClusteredProgrammatic {

    public static void main(String[] args) {
        log.info("编码方式创建集群 CacheManager");
        final URI uri = create("terracotta://localhost:9510/clustered");

        try (CacheManager cacheManager = newCacheManagerBuilder()
                .with(cluster(uri).autoCreate().defaultServerResource("default-resource"))
                .withCache("basicCache",
                        newCacheConfigurationBuilder(Long.class, String.class,
                                heap(100).offheap(1, MB).with(clusteredDedicated(5, MB))))
                .build(true)) {
            Cache<Long, String> basicCache = cacheManager.getCache("basicCache", Long.class, String.class);

            log.info("设置换成");
            basicCache.put(1L, "王爵nice");

            log.info("关闭CacheManager");
        }
        log.info("退出");
    }
}