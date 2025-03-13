package com.solar.calculator.dto;

import com.solar.calculator.entity.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PostQueryRequest {
    @NotBlank(message = "Company name is required")
    String company;
    Panel panel;
    Inverter inverter;
    Battery battery;
    Price price;
    Warehouse warehouse;
}
