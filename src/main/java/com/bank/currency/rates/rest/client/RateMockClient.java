package com.bank.currency.rates.rest.client;

import com.bank.currency.rates.domain.Currency;
import com.bank.currency.rates.dto.RateDto;
import com.bank.currency.rates.mapper.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
@Profile("mock")
public class RateMockClient implements RateSourceClient{

    @Value("${mock.src.path}")
    private String mockDir;

    @Value("${mock.file.name}")
    private String mockDataFile;

    private final ObjectMapper mapper;
    DateTimeFormatter fileNameformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @SneakyThrows
    @Override
    public Map<Integer, Currency> readCurrencies() {
        try {
            RateDto[] rateDtos = readMockRates(mockDir + mockDataFile + ".json");
            return Arrays.stream(rateDtos).map(Mapper::toCurrency).collect(Collectors.toMap(
                    c -> c.getCode(),
                    Function.identity()
            ));
        } catch (IOException e) {
            throw new RuntimeException("error reading mock data", e);
        }
    }

    @Override
    public List<RateDto> readRatesForDate(LocalDate date) {
        try {
            RateDto[] rateDtos = readMockRates(mockDir + fileNameformatter.format(date) + ".json");
            return Arrays.stream(rateDtos).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("no mock data exists for requested date: " + date, e);
        }
    }

    private RateDto[] readMockRates(String filePath) throws IOException {
        log.info("mock data requested from file: {}", filePath);
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        String json = new String(bytes);
        RateDto[] rateDtos = mapper.readValue(json, RateDto[].class);
        log.info("mock data was red as: {}", Arrays.toString(rateDtos));
        return rateDtos;
    }

    @Override
    public List<RateDto> readRates() {
        return readRatesForDate(LocalDate.now());
    }
}
