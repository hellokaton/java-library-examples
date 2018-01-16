package io.github.biezhi.commons.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 自定义属性
 * <p>
 * 自定义日期格式化
 */
public class Example2 {
    private static Log log = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$s] %5$s %n");
        log = LogFactory.getLog(Example2.class);
    }

    public static void main(String[] args) {
        log.info("我爱编程，编程使我快乐。");
    }

}