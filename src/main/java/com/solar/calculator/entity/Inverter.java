package com.solar.calculator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Inverter {
    Integer id;
    String model;
    String manufacturer;
    Integer price;
    Integer warrantyInMonths;
    Long updated_at;
    Long created_at;
}
