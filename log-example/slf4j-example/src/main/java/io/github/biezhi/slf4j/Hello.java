package io.github.biezhi.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author biezhi
 * @date 2018/1/16
 */
public class Hello {

    private static Logger LOGGER = LoggerFactory.getLogger(Hello.class);

    public static void doSomething() {
        LOGGER.info("在 Hello 类中的 INFO 消息");
    }

}
