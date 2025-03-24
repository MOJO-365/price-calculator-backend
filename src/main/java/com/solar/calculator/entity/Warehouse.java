package com.solar.calculator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Warehouse {
    Integer id;
    @JsonProperty("warehouse_name")
    String warehouseName;
    String address;
    Integer postcode;
    State state;
    String latitude;
    String longitude;
    boolean isActive;
    @JsonProperty("updated_at")
    Long updatedAt;
    @JsonProperty("createdAt")
    Long createdAt;

    public enum State {
        NSW, VIC, QLD, SA, WA, TAS, ACT, NT, JBT;
    }
}
