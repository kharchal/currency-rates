package com.bank.currency.rates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"r030", "cc", "rate", "txt", "exchangedate", "updated"})
public class RateDto {

    @JsonProperty("cc")
    private String name;

    @JsonProperty("r030")
    private Integer code;

    @JsonProperty("txt")
    private String localName;

    private float rate;

    @JsonProperty("exchangedate")
    private String date;

    private String updated;
}
