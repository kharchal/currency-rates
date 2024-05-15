package com.bank.currency.rates.mapper;

import com.bank.currency.rates.domain.Currency;
import com.bank.currency.rates.domain.Rate;
import com.bank.currency.rates.dto.RateDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Mapper {

    private static DateTimeFormatter toRateformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static DateTimeFormatter toUpdatedformatter = DateTimeFormatter.ISO_DATE_TIME;
    private static DateTimeFormatter toDateformatter = DateTimeFormatter.ISO_DATE;

    public static Currency toCurrency(RateDto dto) {
        Currency currency = new Currency();
        currency.setCode(dto.getCode());
        currency.setName(dto.getName());
        currency.setLocalName(dto.getLocalName());
        return currency;
    }

    public static Rate toRate(RateDto dto) {
        Rate rate = new Rate();
        rate.setRate(dto.getRate());
        rate.setCode(dto.getCode());
        LocalDate parsed = LocalDate.parse(dto.getDate(), toRateformatter);
        rate.setDate(parsed);
        rate.setUpdated(LocalDateTime.now());
        return rate;
    }

    public static RateDto toRateDto(Currency currency, Rate rate) {
        RateDto dto = new RateDto();
        dto.setCode(rate.getCode());
        dto.setDate(toDateformatter.format(rate.getDate()));
        dto.setRate(rate.getRate());
        dto.setName(currency.getName());
        dto.setLocalName(currency.getLocalName());
        String updated = toUpdatedformatter.format(rate.getUpdated());
        dto.setUpdated(updated);
        return dto;
    }

}
