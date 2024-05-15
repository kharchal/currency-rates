package com.bank.currency.rates.service;

import com.bank.currency.rates.domain.Currency;
import com.bank.currency.rates.domain.Rate;
import com.bank.currency.rates.dto.RateDto;
import com.bank.currency.rates.mapper.Mapper;
import com.bank.currency.rates.repo.CurrencyRepository;
import com.bank.currency.rates.repo.RateRepository;
import com.bank.currency.rates.rest.client.RateSourceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RateService {

    private final CurrencyRepository currencyRepository;
    private final RateRepository rateRepository;

//    private final RateRepositoryImpl rateRepositoryImpl;

    @Autowired
    private final RateSourceClient rateSourceClient;

    private Map<Integer, Currency> currencyCache = new HashMap<>();

    @Transactional
    public List<RateDto> getRatesForDate(LocalDate date) {
        log.info("rates for {} were requested", date);
        updateCurrencyCacheIfNeeded();

        List<Rate> rates = rateRepository.findByDate(date);
        if (rates.isEmpty()) {
            log.info("no rates stored in DB for that date");
            List<RateDto> list = rateSourceClient.readRatesForDate(date);
            log.info("rates were received from attached source");

            List<Rate> rateList = list.stream()
                    .map(Mapper::toRate)
                    .collect(Collectors.toList());

            rateRepository.saveAll(rateList);
            log.info("rates were saved to DB");

            return rateList.stream().map(rateDtoMapping).collect(Collectors.toList());
        }
        log.info("rates were extracted form DB");
        return rates.stream().map(rateDtoMapping).collect(Collectors.toList());
    }

    private Function<Rate, RateDto> rateDtoMapping = r -> Mapper.toRateDto(currencyCache.get(r.getCode()), r);

    public List<RateDto> getRates() {
        return getRatesForDate(LocalDate.now());
    }


    @Transactional
    private void updateCurrencyCacheIfNeeded() {
        if (currencyCache.isEmpty()) {
            currencyCache = rateSourceClient.readCurrencies();
            log.info("currency cache was updated: {}", currencyCache);
//            currencyRepository.deleteAll();
            currencyRepository.saveAll(currencyCache.values());
            log.info("currencies were saved to DB");
        }
    }

    @Transactional
    public List<RateDto> deleteForDate(LocalDate date) {
        rateRepository.deleteByDate(date);
        return Collections.emptyList();
    }
}
