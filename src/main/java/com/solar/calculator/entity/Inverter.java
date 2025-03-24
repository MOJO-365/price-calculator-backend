package com.solar.calculator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("phase")
    Battery.Phase phase;
    @JsonProperty("mppt")
    Integer mppt;
    @JsonProperty("is_hybrid")
    Boolean isHybrid;
    @JsonProperty("warranty_in_months")
    Integer warrantyInMonths;
    @JsonProperty("updated_at")
    Long updatedAt;
    @JsonProperty("created_at")
    Long createdAt;
}
