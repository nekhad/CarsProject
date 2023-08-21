package com.example.carsproject.service.impl;

import com.example.carsproject.dto.request.CarInsertRequest;
import com.example.carsproject.dto.response.CarDTO;
import com.example.carsproject.entity.Car;
import com.example.carsproject.entity.User;
import com.example.carsproject.repository.CarRepository;
import com.example.carsproject.repository.UserRepository;
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
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CarDTO> getALlCarName() {
        List<Car> cars = carRepository.finD();
        List<CarDTO> results = new ArrayList<>();
        System.out.println();
        for (Car car: cars){
            CarDTO carDTO = new CarDTO();
            carDTO.setId(car.getId());
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

//    @Override
//    public List<Car> getAllCars() {
//        List<Car> car1 = carRepository.finD();
//        System.out.println("test" + carRepository.finD());
//        return carRepository.finD();
//    }

    @Override
    public List<CarDTO> getBySpesifiedFields(String id,String name, String model, String colour, String powerOfMotor, Long year, String price) {
       List<Car> carr =  carRepository.getBySpesifiedFields(name,model,colour,powerOfMotor,year,price);
       List<CarDTO> carDTO = new ArrayList<>();
        for (Car car : carr
             ) {
            CarDTO carDTO1 = CarDTO.builder()
                    .id(car.getId())
                    .name(car.getName())
                    .model(car.getModel())
                    .colour(car.getColour())
                    .powerOfMotor(car.getPowerOfMotor())
                    .year(car.getYear())
                    .price(car.getPrice()).build();
            carDTO.add(carDTO1);

        }
        return carDTO;
    }
    @Override
    public List<CarDTO> getCarsOfOwners(String email) {
        List<Car> cars = carRepository.getCarsForOwners(email);
        List<CarDTO> carDTO = new ArrayList<>();

        for (Car car : cars
        ) {
            CarDTO carDTO1 = CarDTO.builder()
                    .name(car.getName())
                    .model(car.getModel())
                    .colour(car.getColour())
                    .powerOfMotor(car.getPowerOfMotor())
                    .year(car.getYear())
                    .price(car.getPrice()).build();
            carDTO.add(carDTO1);

        }
        return carDTO;
    }

    public List<String> getCarsName(){
        return carRepository.getCarsName();
    }
    public List<String> getCarsModel(){
        return carRepository.getCarsModel();
    }
    public List<String> getCarsColour(){
        return carRepository.getCarsColour();
    }
    public List<Long> getCarsYear(){
        return carRepository.getCarsYear();
    }





    @Override
    public void addCar(CarInsertRequest carInsertRequest) {
    Car car = Car.builder()
            .name(carInsertRequest.getName())
            .powerOfMotor(carInsertRequest.getPowerOfMotor())
            .model(carInsertRequest.getModel())
            .colour(carInsertRequest.getColour())
            .price(carInsertRequest.getPrice())
            .year(carInsertRequest.getYear())
            .user(userRepository.getUserByToken(carInsertRequest.getToken())).build();
    carRepository.save(car);
    }

    @Override
    public void updateCarDetails(String id, Car car) {
        Optional<Car> existingCar = carRepository.findCarById(id);
        if (existingCar.isPresent()) {
            car.setId(id);
            car.setColour(car.getColour());
            car.setName(car.getName());
            car.setPrice(car.getPrice());
            car.setModel(car.getModel());
            car.setPowerOfMotor(car.getPowerOfMotor());
            car.setYear(car.getYear());
            car.setUser(car.getUser());
            carRepository.save(car);
        } else {
            throw new RuntimeException("Car is not found to update");
        }
    }

    @Override
    public void deleteCar(String id) {
        Car existingCar = carRepository.findCarById(id).orElse(null);
        if (existingCar !=null) {
            carRepository.delete(existingCar);
        }
        else {
            throw new RuntimeException("Car is not found to delete");
        }
    }
}
