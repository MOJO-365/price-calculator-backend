package com.solar.calculator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solar.calculator.config.GlobalDatabase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralQueryRequest {

    @Min(value = 0, message = "Page number must be 0 or greater")
    @JsonProperty("page_number")
    Integer pageNumber;
    @Min(value = 1, message = "Page size must be at least 1")
    @JsonProperty("page_size")
    Integer pageSize;
    @NotBlank(message = "Company name is required")
    @JsonProperty("company")
    String company;
    @NotEmpty(message = "Columns list cannot be empty")
    @JsonProperty("columns")
    List<String> columns;
    @JsonProperty("filter")
    Map<String, FilterCriteria> filter;
    @JsonProperty("order_by_columns")
    Map<String, String> orderByColumns;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FilterCriteria {
        private GlobalDatabase.FilterType filterType;
        private Object value1;
        private  Object value2;
    }

}
