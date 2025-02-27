package com.oddlycodes.byteprocessor.events;

import com.oddlycodes.byteprocessor.rpc.MediaUploadRpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

public class MediaEncodeCompleteListener implements ApplicationListener<MediaEncodeData> {

    @Autowired
    private MediaUploadRpc rpcServer;

    private final Logger logger = LoggerFactory.getLogger(MediaEncodeCompleteListener.class);

    @Override
    public void onApplicationEvent(MediaEncodeData event) {
        logger.info(event.getMessage());
        rpcServer.callback(event.getFilePath(), event.getFileId());
    }
}
