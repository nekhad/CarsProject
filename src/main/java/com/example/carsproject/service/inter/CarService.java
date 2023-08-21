package com.example.carsproject.service.inter;

import com.example.carsproject.dto.request.CarInsertRequest;
import com.example.carsproject.entity.Car;
import com.example.carsproject.dto.response.CarDTO;

import java.util.List;

public interface CarService {

    List<CarDTO> getALlCarName();

//    List<Car> getAllCars();

    List<CarDTO> getBySpesifiedFields(String id,String name, String model, String colour, String powerOfMotor, Long year, String price);
    List<String> getCarsName();

    List<String> getCarsModel();

    List<String> getCarsColour();

    List<Long> getCarsYear();

    List<CarDTO> getCarsOfOwners(String email);
//    List<CarDTO> getCarByName(String name);
//
//    List<CarDTO> getCarByModel(String model);
//
//    List<CarDTO> getCarByColour(String colour);
//
//    List<CarDTO> getCarBypowerOfMotor(String powerOfMotor);
//
//    List<CarDTO> getCarByYear(Double year);
//
//    List<CarDTO> getCarByPrice(String price);

    void addCar(CarInsertRequest carInsertRequest);

    void updateCarDetails(String id, Car car);

    void deleteCar(String id);


}
