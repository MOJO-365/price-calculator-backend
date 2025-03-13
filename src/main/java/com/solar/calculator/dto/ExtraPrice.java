package com.solar.calculator.dto;

import com.solar.calculator.entity.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtraPrice {
    String name;
    String subId;
    HashMap<String, Price.UnitPrice> option;
}
