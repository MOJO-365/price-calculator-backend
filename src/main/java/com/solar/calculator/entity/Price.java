package com.solar.calculator.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    private Integer id;
    private String name;
    @JsonProperty("sub_id")
    private String subId;
    @JsonProperty("updated_at")
    private long updatedAt;
    private String message;
    private UnitPrice nsw;
    private UnitPrice qld;
    private UnitPrice act;
    private UnitPrice vic;
    private UnitPrice wa;
    private UnitPrice sa;
    private UnitPrice tas;
    private UnitPrice nt;
    private UnitPrice jbt;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitPrice {
        private Price.UNIT key;
        private Double value;
        private String name;
        private String message;

        public UnitPrice(Price.UNIT key,Double value){
            this.key=key;
            this.value=value;
        }
    }

    public enum UNIT {
        PANEL, FIXED, KM, LUMPSUMP
    }
}
