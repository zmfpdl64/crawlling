package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    @Value("${file.dir}")
    private String UPLOAD_DIR;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("files") ArrayList<MultipartFile> files ){
        if(files.isEmpty()){
            return ResponseEntity.badRequest().body("파일을 선택해주세요");
        }
        try{
            for(MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                File destinationFile = new File(UPLOAD_DIR + fileName);
                file.transferTo(destinationFile);
            }
            return ResponseEntity.ok("파일이 성공적으로 업로드되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 중 오류가 발생헀습니다.");
        }
    }
}
