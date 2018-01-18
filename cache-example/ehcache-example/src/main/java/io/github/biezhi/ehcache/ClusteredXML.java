package io.github.biezhi.ehcache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.xml.XmlConfiguration;

import java.net.URL;

import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManager;

/**
 * XML方式创建集群 CacheManager
 */
@Slf4j
public class ClusteredXML {

    public static void main(String[] args) {
        log.info("XML方式创建集群 CacheManager");
        final URL     myUrl     = ClusteredXML.class.getResource("/clustered-ehcache.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        try (CacheManager cacheManager = newCacheManager(xmlConfig)) {
            cacheManager.init();

            Cache<Long, String> basicCache = cacheManager.getCache("basicCache", Long.class, String.class);

            String value = basicCache.get(1L);
            log.info("读取缓存: {}", value);

            log.info("关闭 CacheManager");
        }
        log.info("退出");
    }
}