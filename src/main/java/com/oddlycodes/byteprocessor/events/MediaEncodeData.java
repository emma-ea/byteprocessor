package com.oddlycodes.byteprocessor.events;

import org.springframework.context.ApplicationEvent;

public class MediaEncodeData extends ApplicationEvent {

    private String message;

    public MediaEncodeData(Object source, String message) {
        super(source);
    }

    public String getMessage() {
        return message;
    }
}
