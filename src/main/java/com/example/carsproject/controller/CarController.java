package com.example.carsproject.controller;

import com.example.carsproject.dto.request.CarInsertRequest;
import com.example.carsproject.dto.request.CarRequestDTO;
import com.example.carsproject.dto.request.TokenRequest;
import com.example.carsproject.entity.Car;
import com.example.carsproject.dto.response.CarDTO;
import com.example.carsproject.entity.User;
import com.example.carsproject.service.inter.CarService;
import com.example.carsproject.service.inter.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private FileService fileService;

    @PostMapping("/filter")
    public ResponseEntity<List<CarDTO>> getBySpesifiedFields(@RequestBody CarRequestDTO carRequestDTO) {
        return ResponseEntity.ok(carService.getBySpesifiedFields(carRequestDTO.id(),carRequestDTO.name(),carRequestDTO.model(),carRequestDTO.colour(),carRequestDTO.powerOfMotor(),carRequestDTO.year(),carRequestDTO.price()));
    }
//    @PostMapping("/ownCars")
//    public ResponseEntity<List<CarDTO>> getCarsForOwners(@RequestParam String token) {
//        return ResponseEntity.ok(carService.getCarsOfOwners(token));
//    }

    @PostMapping("/ownCars")
    public ResponseEntity<List<CarDTO>> getCarsForOwners(@RequestBody TokenRequest tokenRequest) {
        String token = tokenRequest.getToken();
        return ResponseEntity.ok(carService.getCarsOfOwners(token));
    }

//    @PostMapping("/ownCars")
//    public ResponseEntity<List<CarDTO>> getCarsForOwners(@RequestBody Map<String, String> requestBody) {
//        String token = requestBody.get("token");
//        return ResponseEntity.ok(carService.getCarsOfOwners(token));
//    }
//    @GetMapping("/")
//    public ResponseEntity<List<CarDTO>> getAllCarsTest() {
//        return ResponseEntity.ok(carService.getALlCarName());
//    }

    @GetMapping("/names")
    public List<String> getCarNames() {
        return carService.getCarsName();
    }
    @GetMapping("/model")
    public List<String> getCarsModel() {
        return carService.getCarsModel();
    }

    @GetMapping("/colour")
    public List<String> getCarsColur() {
        return carService.getCarsColour();
    }
    @GetMapping("/year")
    public List<Long> getCarsYear() {
        return carService.getCarsYear();
    }
    @PostMapping("/add")
    public void addCar(@RequestBody CarInsertRequest carInsertRequest) {
        carService.addCar(carInsertRequest);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam String fkCarId) {
        try {

            fileService.uploadFile(file,fkCarId);
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file.");
        }
    }

    @PutMapping("/{id}")
    public void updateCar(@PathVariable String id, @RequestBody Car car) {
        carService.updateCarDetails(id, car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable String id) {
        carService.deleteCar(id);
    }

//    @GetMapping("/OfName")
//    public ResponseEntity<List<CarDTO>> getCarsByName(@RequestParam String name) {
//        return ResponseEntity.ok(carService.getCarByName(name));
//    }
//    @GetMapping("/OfModel")
//    public ResponseEntity<List<CarDTO>> getCarByModel(@RequestParam String model) {
//        return ResponseEntity.ok(carService.getCarByModel(model));
//    }
//@GetMapping("/OfColour")
//public ResponseEntity<List<CarDTO>> getCarByColour(@RequestParam String colour) {
//    return ResponseEntity.ok(carService.getCarByColour(colour));
//}
//    @GetMapping("/OfpowerOfMotor")
//    public ResponseEntity<List<CarDTO>> getCarBypowerOfMotor(@RequestParam String powerOfMotor) {
//        return ResponseEntity.ok(carService.getCarBypowerOfMotor(powerOfMotor));
//    }
//
//    @GetMapping("/OfYear")
//    public ResponseEntity<List<CarDTO>> getCarByYear(@RequestParam Double year) {
//        return ResponseEntity.ok(carService.getCarByYear(year));
//    }
//    @GetMapping("/OfPrice")
//    public ResponseEntity<List<CarDTO>> getCarByPrice(@RequestParam String price) {
//        return ResponseEntity.ok(carService.getCarByPrice(price));
//    }

//    @GetMapping("/get")
//    public ResponseEntity<List<Car>> getAllCars() {
//        return ResponseEntity.ok(carService.getAllCars());
//    }

}