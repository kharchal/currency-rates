package com.bank.currency.rates.rest.client;

import com.bank.currency.rates.domain.Currency;
import com.bank.currency.rates.dto.RateDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public interface RateSourceClient {

    Map<Integer, Currency> readCurrencies();
    List<RateDto> readRatesForDate(LocalDate date);
    List<RateDto> readRates();
}
