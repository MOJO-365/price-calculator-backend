package com.solar.calculator.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    Integer id;
    String name;
    String msg;
    String unit;
    Double price;
}
