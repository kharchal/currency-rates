package com.bank.currency.rates.domain;


import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode
public class RateId implements Serializable {

    private Integer code;
    private LocalDate date;

    public RateId() {
    }

    public RateId(Integer code, LocalDate date) {
        this.code = code;
        this.date = date;
    }
}
