package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.channels.MulticastChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    @Value("${file.dir}")
    private String UPLOAD_DIR;
    private List<String> upload_path = new ArrayList<>();

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("files") ArrayList<MultipartFile> files ){
        if(files.isEmpty()){
            return ResponseEntity.badRequest().body("파일을 선택해주세요");
        }
        try{
            for(MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                File destinationFile = new File(UPLOAD_DIR + fileName);
                upload_path.add(UPLOAD_DIR+fileName);
                file.transferTo(destinationFile);
            }
            return ResponseEntity.ok("파일이 성공적으로 업로드되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 중 오류가 발생헀습니다.");
        }
    }

    @GetMapping("/download")
    public ResponseEntity<List<Resource>> downloadFile() throws IOException {

        List<Resource> resources = new ArrayList<> ();
        for (String i : upload_path) {
            resources.add(new UrlResource(Paths.get(i).toUri()));
        }
        List<MediaType> contentTypes = new ArrayList<>();
        for(String path : upload_path){
            MediaType contentType = MediaType.parseMediaType(Files.probeContentType(Paths.get(path)));
            contentTypes.add(contentType);
        }
        MultiValueMap<String, Resource> body = new LinkedMultiValueMap<>();
        for(int i = 0; i < resources.size(); i++) {
            body.add("files", resources.get(i));
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body((List<Resource>) body);
    }
}
