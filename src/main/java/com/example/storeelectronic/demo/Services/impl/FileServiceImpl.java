package com.example.storeelectronic.demo.Services.impl;

import com.example.storeelectronic.demo.Services.Fileservice;
import com.example.storeelectronic.demo.exception.BadClassException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements Fileservice {


    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String uploadFile(MultipartFile file, String path) {
        String originalFileName = file.getOriginalFilename();
        logger.info("filename:{}", originalFileName);

        String filename = UUID.randomUUID().toString();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String filenamewithextension = filename + extension;
        String fullpathwithfilename = path + File.separator + filenamewithextension;

        if (extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".png")) {
            try {
                File folder = new File(path);
                if (!folder.exists()) {
                    folder.mkdirs();

                }
                Files.copy(file.getInputStream(), Paths.get(fullpathwithfilename));
return filenamewithextension;

            } catch (IOException e) {
                throw new RuntimeException("Failed to upload file", e);
            }
        } else {
            throw new BadClassException("Only JPG and PNG files are allowed");
        }
    }

    @Override
    public InputStream getResource(String path, String name) {
        String fullpath = path + File.separator + name;
        try {
            return new FileInputStream(fullpath);
        } catch (IOException e) {
            throw new RuntimeException("File not found at path: " + fullpath, e);
        }
    }
}
