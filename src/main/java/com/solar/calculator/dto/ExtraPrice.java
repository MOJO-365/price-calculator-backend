package com.solar.calculator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solar.calculator.entity.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtraPrice {
    @JsonProperty("option_id")
    String optionId;
    List<Price.UnitPrice> price_list;
}