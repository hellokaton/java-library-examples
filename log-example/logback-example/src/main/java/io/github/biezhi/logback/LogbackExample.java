package io.github.biezhi.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackExample {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogbackExample.class);

	public static void main(String[] args) {
		LOGGER.debug("我爱编程，编程使我快乐。");
		LOGGER.info("我爱编程，编程使我快乐。");
		LOGGER.warn("我爱编程，编程使我快乐。");
		LOGGER.error("我爱编程，编程使我快乐。");
	}

}