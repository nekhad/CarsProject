package com.example.carsproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private String colour;
    private String model;
    private String name;
    private Long year;
    private String powerOfMotor;
    private String price;

}
