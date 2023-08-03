package com.example.carsproject.service.impl;

import com.example.carsproject.dto.CarDTO;
import com.example.carsproject.entity.Car;
import com.example.carsproject.repository.CarRepository;
import com.example.carsproject.service.inter.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<CarDTO> getALlCarName() {
        List<Car> cars = carRepository.finD();
        List<CarDTO> results = new ArrayList<>();
        System.out.println();
        for (Car car: cars){
            CarDTO carDTO = new CarDTO();
            carDTO.setName(car.getName());
            carDTO.setColour(car.getColour());
            carDTO.setYear(car.getYear());
            carDTO.setPrice(car.getPrice());
            carDTO.setModel(car.getModel());
            carDTO.setPowerOfMotor(car.getPowerOfMotor());
            results.add(carDTO);
        }
        return results;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> car1 = carRepository.finD();
        System.out.println("test" + carRepository.finD());
        return carRepository.finD();
    }



    @Override
    public List<CarDTO> getCarByName(String name) {
        List<Car> cars = carRepository.finByNameOfCars(name);
        List<CarDTO> results = new ArrayList<>();
        System.out.println();
        for (Car car: cars){
            CarDTO carDTO = new CarDTO();
            carDTO.setName(car.getName());
            carDTO.setColour(car.getColour());
            carDTO.setYear(car.getYear());
            carDTO.setPrice(car.getPrice());
            carDTO.setModel(car.getModel());
            carDTO.setPowerOfMotor(car.getPowerOfMotor());
            results.add(carDTO);
        }
        return results;
    }

    @Override
    public List<CarDTO> getCarByModel(String model) {
//        return carRepository.findByNameOfModels(model);
        List<Car> cars = carRepository.findByNameOfModels(model);
        List<CarDTO> results = new ArrayList<>();
        System.out.println();
        for (Car car: cars){
            CarDTO carDTO = new CarDTO();
            carDTO.setName(car.getName());
            carDTO.setColour(car.getColour());
            carDTO.setYear(car.getYear());
            carDTO.setPrice(car.getPrice());
            carDTO.setModel(car.getModel());
            carDTO.setPowerOfMotor(car.getPowerOfMotor());
            results.add(carDTO);
        }
        return results;
    }


    @Override
    public List<CarDTO> getCarByColour(String colour) {
//        return carRepository.findByColour(colour);
        List<Car> cars = carRepository.findByColour(colour);
        List<CarDTO> results = new ArrayList<>();
        System.out.println();
        for (Car car: cars){
            CarDTO carDTO = new CarDTO();
            carDTO.setName(car.getName());
            carDTO.setColour(car.getColour());
            carDTO.setYear(car.getYear());
            carDTO.setPrice(car.getPrice());
            carDTO.setModel(car.getModel());
            carDTO.setPowerOfMotor(car.getPowerOfMotor());
            results.add(carDTO);
        }
        return results;
    }

    @Override
    public List<CarDTO> getCarBypowerOfMotor(String powerOfMotor) {
//        return carRepository.finBypowerOfMotor(powerOfMotor);
        List<Car> cars = carRepository.finBypowerOfMotor(powerOfMotor);
        List<CarDTO> results = new ArrayList<>();
        System.out.println();
        for (Car car: cars){
            CarDTO carDTO = new CarDTO();
            carDTO.setName(car.getName());
            carDTO.setColour(car.getColour());
            carDTO.setYear(car.getYear());
            carDTO.setPrice(car.getPrice());
            carDTO.setModel(car.getModel());
            carDTO.setPowerOfMotor(car.getPowerOfMotor());
            results.add(carDTO);
        }
        return results;
    }

    @Override
    public List<CarDTO> getCarByYear(Double year) {
//        return carRepository.finByYear(year);
        List<Car> cars = carRepository.finByYear(year);
        List<CarDTO> results = new ArrayList<>();
        System.out.println();
        for (Car car: cars){
            CarDTO carDTO = new CarDTO();
            carDTO.setName(car.getName());
            carDTO.setColour(car.getColour());
            carDTO.setYear(car.getYear());
            carDTO.setPrice(car.getPrice());
            carDTO.setModel(car.getModel());
            carDTO.setPowerOfMotor(car.getPowerOfMotor());
            results.add(carDTO);
        }
        return results;
    }

    @Override
    public List<CarDTO> getCarByPrice(String price) {
//        return carRepository.finByPrice(price);
        List<Car> cars = carRepository.finByPrice(price);
        List<CarDTO> results = new ArrayList<>();
        System.out.println();
        for (Car car: cars){
            CarDTO carDTO = new CarDTO();
            carDTO.setName(car.getName());
            carDTO.setColour(car.getColour());
            carDTO.setYear(car.getYear());
            carDTO.setPrice(car.getPrice());
            carDTO.setModel(car.getModel());
            carDTO.setPowerOfMotor(car.getPowerOfMotor());
            results.add(carDTO);
        }
        return results;
    }

    @Override
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void updateCarDetails(Long id, Car car) {
        Optional<Car> existingCar = carRepository.findById(id);
        if (existingCar.isPresent()) {
//            car =  Car.builder()
//                    .colour(car.getColour())
//                    .build();
            car.setId(id);
            car.setColour(car.getColour());
            car.setName(car.getName());
            car.setPrice(car.getPrice());
            car.setModel(car.getModel());
            car.setPowerOfMotor(car.getPowerOfMotor());
            car.setYear(car.getYear());
            car.setFkOwnerId(car.getFkOwnerId());
            carRepository.save(car);
        } else {
            throw new RuntimeException("Car is not found");
        }
    }

    @Override
    public void deleteCar(Long id) {

    }
}
