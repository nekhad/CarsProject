package com.example.carsproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarInsertRequest {
    private String name;
    private String model;
    private String colour;
    private String powerOfMotor;
    private Long year;
    private String price;
    private String token;
}
