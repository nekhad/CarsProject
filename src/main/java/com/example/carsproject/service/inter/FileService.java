package com.example.carsproject.service.inter;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    void uploadFile(MultipartFile multipartFile,String fkCarId) throws IOException;
}
