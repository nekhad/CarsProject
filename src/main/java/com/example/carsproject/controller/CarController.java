package com.example.carsproject.controller;

import com.example.carsproject.entity.Car;
import com.example.carsproject.dto.CarDTO;
import com.example.carsproject.service.inter.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/get")
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }


    @GetMapping("/getTest")
    public ResponseEntity<List<CarDTO>> getAllCarsTest() {
        return ResponseEntity.ok(carService.getALlCarName());
    }

    @GetMapping("/OfName")
    public ResponseEntity<List<CarDTO>> getCarsByName(@RequestParam String name) {
        return ResponseEntity.ok(carService.getCarByName(name));
    }
    @GetMapping("/OfModel")
    public ResponseEntity<List<CarDTO>> getCarByModel(@RequestParam String model) {
        return ResponseEntity.ok(carService.getCarByModel(model));
    }
//    @GetMapping("/OfModel")
//    public List<Car> getCarByModel(@RequestParam String model) {
//        return carService.getCarByModel(model);
//    }
@GetMapping("/OfColour")
public ResponseEntity<List<CarDTO>> getCarByColour(@RequestParam String colour) {
    return ResponseEntity.ok(carService.getCarByColour(colour));
}
//    @GetMapping("/OfColour")
//    public List<Car> getCarByColour(@RequestParam String colour) {
//        return carService.getCarByColour(colour);
//    }

//    @GetMapping("/OfpowerOfMotor")
//    public List<Car> getCarBypowerOfMotor(@RequestParam String powerOfMotor) {
//        return carService.getCarBypowerOfMotor(powerOfMotor);
//    }

    @GetMapping("/OfpowerOfMotor")
    public ResponseEntity<List<CarDTO>> getCarBypowerOfMotor(@RequestParam String powerOfMotor) {
        return ResponseEntity.ok(carService.getCarBypowerOfMotor(powerOfMotor));
    }

//    @GetMapping("/OfYear")
//    public List<Car> getCarByYear(@RequestParam Double year) {
//        return carService.getCarByYear(year);
//    }

    @GetMapping("/OfYear")
    public ResponseEntity<List<CarDTO>> getCarByYear(@RequestParam Double year) {
        return ResponseEntity.ok(carService.getCarByYear(year));
    }
//    @GetMapping("/OfPrice")
//    public List<Car> getCarByPrice(@RequestParam String price) {
//        return carService.getCarByPrice(price);
//    }
//
    @GetMapping("/OfPrice")
    public ResponseEntity<List<CarDTO>> getCarByPrice(@RequestParam String price) {
        return ResponseEntity.ok(carService.getCarByPrice(price));
    }
    @PostMapping("/add")
    public void addCar(@RequestBody Car car) {
        carService.addCar(car);
    }

    @PutMapping("/{id}")
    public void updateCar(@PathVariable Long id, @RequestBody Car car) {
        carService.updateCarDetails(id, car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}