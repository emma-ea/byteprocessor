package com.oddlycodes.byteprocessor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class AsyncMediaEncodeEventConfig {

    @Bean(name = "mediaEncodeCompleter")
    public ApplicationEventMulticaster eventMulticaster() {
        SimpleApplicationEventMulticaster s = new SimpleApplicationEventMulticaster();
        s.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return s;
    }

}
