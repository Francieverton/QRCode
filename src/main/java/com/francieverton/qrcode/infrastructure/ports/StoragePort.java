package com.francieverton.qrcode.infrastructure.ports;

public interface StoragePort {

    String uploadFile (byte[] bytesPNG, String fileName, String contentType);
}
