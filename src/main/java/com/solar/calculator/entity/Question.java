package com.solar.calculator.entity;

import io.swagger.v3.core.util.Json;
import lombok.*;
import jakarta.validation.constraints.*;

@Data
public class Question {
    private Integer id;
    private String name;
    private Json question;
}
