package com.example.storeelectronic.demo.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
@Service
public interface Fileservice {
    public String uploadFile(MultipartFile file, String path);

    InputStream getResource(String path,String name);
}