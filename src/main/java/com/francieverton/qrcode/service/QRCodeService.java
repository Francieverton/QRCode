package com.francieverton.qrcode.service;

import com.francieverton.qrcode.dto.QRCodeRequest;
import com.francieverton.qrcode.dto.QRCodeResponse;
import com.francieverton.qrcode.infrastructure.ports.StoragePort;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class QRCodeService {

    private final StoragePort storagePort;

    public QRCodeService(StoragePort storagePort) {
        this.storagePort = storagePort;
    }

    public QRCodeResponse generateQRCode (QRCodeRequest request) throws WriterException, IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(request.url(), BarcodeFormat.QR_CODE, 400, 400);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);

        byte[] bytesPNG = byteArrayOutputStream.toByteArray();

        String imageURL = storagePort.uploadFile(bytesPNG, UUID.randomUUID().toString()+".png","image/png");

        return new QRCodeResponse(imageURL);
    }
}
