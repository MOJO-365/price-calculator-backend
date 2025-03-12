package com.solar.calculator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Battery {
    Integer id;
    String modelName;
    String manufacturer;
    Integer warrantyInMonths;
    Integer price;
    Integer capacity;
}
