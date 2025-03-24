package com.solar.calculator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.core.util.Json;
import lombok.Data;

@Data
public class QuestionData {
    String company;
    @JsonProperty("customer_name")
    String customerName;
    @JsonProperty("phone_number")
    String phoneNumber;
    @JsonProperty("question")
    Json question;
}
