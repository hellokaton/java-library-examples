package io.github.biezhi.commons.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 基础示例
 */
public class Example1 {

    private static Log log = LogFactory.getLog(Example1.class);

    public static void main(String[] args) {
        // debug不输出，默认为 info 级别
        log.debug("我爱编程，编程使我快乐。");
        log.info("我爱编程，编程使我快乐。");
        log.warn("我爱编程，编程使我快乐。");
        log.error("我爱编程，编程使我快乐。");
    }

}