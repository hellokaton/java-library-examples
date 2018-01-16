package io.github.biezhi.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Slf4j + logback 示例
 *
 * @author biezhi
 * @date 2018/1/16
 */
public class Slf4jExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jExample.class);

    public static void main(String[] args) {
        LOGGER.debug("我爱编程，编程使我快乐。");
        LOGGER.info("我爱编程，编程使我快乐。");
        LOGGER.warn("我爱编程，编程使我快乐。");
        LOGGER.error("我爱编程，编程使我快乐。");
        Hello.doSomething();
    }

}
