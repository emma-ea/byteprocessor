package com.oddlycodes.byteprocessor.rpc;

import com.oddlycodes.byteprocessor.rpc.MediaUploadServiceGrpc.MediaUploadServiceImplBase;
import com.oddlycodes.byteprocessor.proc.MediaProcessor;
import io.grpc.stub.StreamObserver;
import com.oddlycodes.byteprocessor.rpc.UploadResponse;
import com.oddlycodes.byteprocessor.rpc.UploadRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class MediaUploadRpc extends MediaUploadServiceImplBase implements MediaUploadCallback {

    private final Logger logger = LoggerFactory.getLogger(MediaUploadRpc.class);

    @Autowired
    private MediaProcessor processor;

    private StreamObserver<UploadResponse> streamObserver;

    @Override
    public void mediaUpload(UploadRequest request, StreamObserver<UploadResponse> responseObserver) {

        String fileId = request.getFileId();
        String filePath = request.getFilePath();

        streamObserver = responseObserver;

        logger.info("Starting: processor.mp4Encoder triggered. Encoding started");

        processor.mp4Encoder(filePath, fileId);

        logger.info("Ending: processor.mp4Encoder call complete.");

    }

    @Override
    public void callback(String fileUrl, String fileId) {
        UploadResponse response = UploadResponse.newBuilder()
                .setFileProcessedPath(fileUrl)
                .setFileId(fileId)
                .build();
        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }
}
