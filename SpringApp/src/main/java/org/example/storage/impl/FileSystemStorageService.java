package org.example.storage.impl;

import org.example.storage.StorageProperties;
import org.example.storage.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void init() throws IOException {
        if(!Files.exists(rootLocation))
            Files.createDirectory(rootLocation);
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {

        String randomFileName = java.util.UUID.randomUUID().toString()+"."+getFileExtension(file);

        Path destinationFile = this.rootLocation.resolve(randomFileName).normalize().toAbsolutePath();
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
        }
        return randomFileName;
    }

    private String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        }
        return ""; // Return an empty string if no extension is found
    }

}