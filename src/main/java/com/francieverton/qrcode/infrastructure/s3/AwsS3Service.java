package com.francieverton.qrcode.infrastructure.s3;

import com.francieverton.qrcode.infrastructure.ports.StoragePort;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;

@Service
public class AwsS3Service implements StoragePort {

    private final S3Client s3Client;

    public AwsS3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String uploadFile(byte[] bytesPNG, String fileName, String contentType) {
        return "";
    }
}
