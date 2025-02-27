package com.oddlycodes.byteprocessor.watcher;

import com.oddlycodes.byteprocessor.controller.WatcherController;
import com.oddlycodes.byteprocessor.events.MediaEncodeCompleteListener;
import com.oddlycodes.byteprocessor.events.MediaEncodeData;
import jakarta.annotation.PostConstruct;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;
import net.bramp.ffmpeg.progress.Progress;
import net.bramp.ffmpeg.progress.ProgressListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MediaProcessor {

    private Logger logger = LoggerFactory.getLogger(WatcherController.class);

    @Value("{ffmpeg.path}")
    private String ffmpegPath;

    @Value("{ffmpeg.probe.path}")
    private String probePath;

    @Value("{processor.output}")
    private String processedOutput;

    @Autowired
    private ApplicationEventMulticaster mediaEncodeCompleter;

    private FFmpeg fFmpeg;
    private FFprobe fFprobe;

    @PostConstruct
    public void initialize() {
        try {
            fFmpeg = new FFmpeg(ffmpegPath);
            fFprobe = new FFprobe(probePath);
            logger.info("initializing ffmpeg");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void mp4Encoder(String inputFile) {

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(inputFile)
                .addOutput("output.mp4")
                .setFormat("mp4")
                .disableSubtitle()
                .setAudioChannels(1)
                .setAudioCodec("acc")
                .setAudioSampleRate(48_000)
                .setVideoCodec("libx264")
                .setVideoFrameRate(24, 1)
                .setVideoResolution(640, 480)
                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(fFmpeg, fFprobe);
        // executor.createJob(builder).run();

        FFmpegJob job = executor.createJob(builder, new ProgressListener() {

            // final double duration_ns = in

            @Override
            public void progress(Progress progress) {
                // double percentage = progress.out_time_ns / duration_ns;
                // TODO: progress report
                if (progress.status == Progress.Status.END) {
                    MediaEncodeData encodeData = new MediaEncodeData(this, "media encoding complete");
                    mediaEncodeCompleter.multicastEvent(encodeData);
                }
            }
        });

        job.run();


    }


}
