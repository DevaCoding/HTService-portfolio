package com.springboot2.htservice.web;

import com.springboot2.htservice.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class S3Controller {

    private final S3Uploader s3Uploader;

    @GetMapping("/test")
    public String index() {
        return "test";
    }



    @PostMapping("/images")
    public String upload(@RequestParam("images")MultipartFile multipartFile) throws IOException{
        s3Uploader.upload(multipartFile, "static");
        return "test";
    }
}
