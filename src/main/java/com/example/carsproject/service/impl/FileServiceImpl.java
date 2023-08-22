package com.example.carsproject.service.impl;

import com.example.carsproject.entity.FileEntity;
import com.example.carsproject.repository.CarRepository;
import com.example.carsproject.repository.FileRepository;
import com.example.carsproject.service.inter.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private CarRepository carRepository;

    public void uploadFile(MultipartFile multipartFile,String fkCarId) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilename(multipartFile.getOriginalFilename());
        fileEntity.setContentType(multipartFile.getContentType());
        fileEntity.setData(multipartFile.getBytes());
        fileEntity.setFkCarId(carRepository.findById(fkCarId).orElseThrow(()->new RuntimeException("Car Id gelmedi")));

        fileRepository.save(fileEntity);
    }
}

