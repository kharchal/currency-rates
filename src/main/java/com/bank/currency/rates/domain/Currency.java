package com.bank.currency.rates.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    private Integer code;

    private String name;

    private String localName;

}
