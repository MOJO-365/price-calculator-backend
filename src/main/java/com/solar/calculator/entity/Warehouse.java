package com.solar.calculator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Warehouse {
    Integer id;
    String companyName;
    String address;
    Integer postcode;
    State state;
    boolean isActive;
    Long updatedAt;
    Long createdAt;
    public enum State {
        NSW, VIC, QLD, SA, WA, TAS, ACT, NT, JBT;
    }
}
