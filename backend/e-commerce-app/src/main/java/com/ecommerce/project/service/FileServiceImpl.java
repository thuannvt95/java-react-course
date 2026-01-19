package com.ecommerce.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        // Generate a unique filename using uuid
        String filename = java.util.UUID.randomUUID().toString();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        filename = filename + extension;

        // Check if the directory exists, if not create it
        java.io.File dir = new java.io.File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            // Save the file to the specified path
            Path filePath = Paths.get(path, filename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
