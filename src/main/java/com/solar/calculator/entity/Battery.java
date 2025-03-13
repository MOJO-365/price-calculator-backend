package com.solar.calculator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Battery {
    Integer id;
    @JsonProperty("model_name")
    String modelName;
    String manufacturer;
    @JsonProperty("warranty_in_months")
    Integer warrantyInMonths;
    Integer price;
    Integer capacity;
}
