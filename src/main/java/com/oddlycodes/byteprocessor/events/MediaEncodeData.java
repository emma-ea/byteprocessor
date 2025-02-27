package com.oddlycodes.byteprocessor.events;

import org.springframework.context.ApplicationEvent;

public class MediaEncodeData extends ApplicationEvent {

    private String message;
    private String filePath;
    private String fileId;

    public MediaEncodeData(Object source, String message, String filePath, String fileId) {
        super(source);
        this.message = message;
        this.filePath = filePath;
        this.fileId = fileId;
    }

    public String getMessage() {
        return message;
    }

    public String getFilePath() { return filePath; }

    public String getFileId() { return fileId; }
}
