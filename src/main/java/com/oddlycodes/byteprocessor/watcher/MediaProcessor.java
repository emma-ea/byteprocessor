package com.oddlycodes.byteprocessor.watcher;

import com.oddlycodes.byteprocessor.controller.WatcherController;
import jakarta.annotation.PostConstruct;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MediaProcessor {

    private Logger logger = LoggerFactory.getLogger(WatcherController.class);

    @Value("{ffmpeg.path}")
    private String ffmpegPath;

    private FFmpeg fFmpeg;
    private FFprobe fFprobe;

    @PostConstruct
    public void initialize() {

        try {
            fFmpeg = new FFmpeg(ffmpegPath);
            logger.info("initializing ffmpeg");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }


}
