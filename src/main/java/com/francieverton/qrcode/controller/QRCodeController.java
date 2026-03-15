package com.francieverton.qrcode.controller;

import com.francieverton.qrcode.dto.QRCodeRequest;
import com.francieverton.qrcode.dto.QRCodeResponse;
import com.francieverton.qrcode.service.QRCodeService;
import com.google.zxing.WriterException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @PostMapping("/send")
    public ResponseEntity<QRCodeResponse> request (@RequestBody QRCodeRequest url) throws IOException, WriterException {

        return ResponseEntity.ok(qrCodeService.generateQRCode(url));
    }
}
