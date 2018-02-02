package io.github.biezhi.log4j;

import org.apache.log4j.Logger;

public class Example {

    final static Logger LOGGER = Logger.getLogger(Example.class);

    public static void main(String[] args) {
        LOGGER.debug("我爱编程，编程使我快乐。");
        LOGGER.info("我爱编程，编程使我快乐。");
        LOGGER.warn("我爱编程，编程使我快乐。");
        LOGGER.error("我爱编程，编程使我快乐。");
    }

}