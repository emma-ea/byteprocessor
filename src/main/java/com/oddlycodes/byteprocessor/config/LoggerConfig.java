package com.oddlycodes.byteprocessor.config;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LoggerConfig {

    // @Bean
    public Logger getLogger() {
        //Logger logger = LoggerFactory.getLogger();
        return null;
    }

}
