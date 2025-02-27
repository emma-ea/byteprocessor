package com.oddlycodes.byteprocessor.rpc;

@FunctionalInterface
public interface MediaUploadCallback {

    void callback(String fileUrl, String fileId);

}
