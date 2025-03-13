package com.solar.calculator.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @NotNull
    private Integer id;

    @NotBlank
    private String title;

    @NotNull
    private List<String> diffWaysToSay;

    @NotNull
    private AnswerType answerType;

    private boolean doesTriggerPrice;

    @Min(0)
    private int priceChange;

    private String unit;

    @NotBlank
    private String placeholder;

    private String imageLink;

    public enum AnswerType {
        NUMBER, TEXT, BOOLEAN, DATE
    }

}
