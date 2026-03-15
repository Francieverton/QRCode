package com.francieverton.qrcode.controller;

import com.francieverton.qrcode.dto.QRCodeRequest;
import com.francieverton.qrcode.dto.QRCodeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    @PostMapping("/send")
    public ResponseEntity<QRCodeResponse> request (@RequestBody QRCodeRequest url){
        return ResponseEntity.ok().build();
    }
}
