package io.github.biezhi.ehcache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.xml.XmlConfiguration;
import org.slf4j.Logger;

import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManager;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * XML方式创建缓存管理器
 */
@Slf4j
public class BasicXML {

    public static void main(String[] args) {
        log.info("XML方式创建 CacheManager");

        Configuration xmlConfig = new XmlConfiguration(BasicXML.class.getResource("/ehcache.xml"));
        try (CacheManager cacheManager = newCacheManager(xmlConfig)) {
            cacheManager.init();

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