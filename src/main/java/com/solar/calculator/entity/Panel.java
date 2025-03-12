package com.solar.calculator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Panel {
    Integer id;
    String model;
    String manufacturer;
    @JsonProperty("warranty_in_months")
    Integer warrantyInMonths;
    Integer watt;
    Double price;
    @JsonProperty("updated_at")
    Long updatedAt;
}
