package com.oddlycodes.byteprocessor.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

public class MediaEncodeCompleteListener implements ApplicationListener<MediaEncodeData> {

    private final Logger logger = LoggerFactory.getLogger(MediaEncodeCompleteListener.class);

    @Override
    public void onApplicationEvent(MediaEncodeData event) {
        logger.info(event.getMessage());
    }
}
