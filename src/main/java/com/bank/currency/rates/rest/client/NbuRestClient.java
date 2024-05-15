package com.bank.currency.rates.rest.client;

import com.bank.currency.rates.domain.Currency;
import com.bank.currency.rates.dto.RateDto;
import com.bank.currency.rates.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile("dev")
public class NbuRestClient implements RateSourceClient {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Value("${nbu.current.rates.url}")
    private String currentRatesUrl;

    @Value("${nbu.date.rates.url}")
    private String ratesForDateUrl;

//    List<String> allowedTypes = List.of("USD", "PLN", "GBP", "CAD");
//    Predicate<RateDto> predicate = rateDto -> allowedTypes.contains(rateDto.getName());

    private final RestTemplate restTemplate;

    public List<RateDto> readRates() {
        ResponseEntity<RateDto[]> response = restTemplate.getForEntity(currentRatesUrl, RateDto[].class);
        RateDto[] body = response.getBody();
        return Arrays.stream(body)
//                .filter(predicate)
                .collect(Collectors.toList());
    }

    public Map<Integer, Currency> readCurrencies() {
        List<RateDto> rateDtos = readRates();
        return rateDtos.stream()
                .map(Mapper::toCurrency)
                .collect(Collectors.toMap(
                        c -> c.getCode(),
                        Function.identity())
                );
    }

    public List<RateDto> readRatesForDate(LocalDate date) {
        String requestUrl = ratesForDateUrl.replace("{}", formatter.format(date));
        ResponseEntity<RateDto[]> response = restTemplate.getForEntity(requestUrl, RateDto[].class);
        RateDto[] body = response.getBody();
        return Arrays.stream(body)
//                .filter(predicate)
                .collect(Collectors.toList());
    }
}
