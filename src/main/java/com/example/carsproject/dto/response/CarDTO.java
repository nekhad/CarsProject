package com.example.carsproject.dto.response;

import lombok.Data;

@Data
public class CarDTO {
    private String colour;
    private String model;
    private String name;
    private Long year;
    private String powerOfMotor;
    private String price;

}
