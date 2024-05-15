package com.bank.currency.rates.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rates")
@IdClass(RateId.class)
public class Rate {

    @Id
    private Integer code;

    @Id
    private LocalDate date;

    private float rate;

    private LocalDateTime updated;

}
