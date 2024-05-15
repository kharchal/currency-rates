package com.bank.currency.rates.service;

import com.bank.currency.rates.domain.Currency;
import com.bank.currency.rates.domain.Rate;
import com.bank.currency.rates.dto.RateDto;
import com.bank.currency.rates.mapper.Mapper;
import com.bank.currency.rates.repo.CurrencyRepository;
import com.bank.currency.rates.repo.RateRepository;
import com.bank.currency.rates.rest.client.RateSourceClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class RateServiceTest {

    @InjectMocks
    private RateService rateService;

    @Mock
    private CurrencyRepository currencyRepositoryMock;

    @Mock
    private RateRepository rateRepositoryMock;

    @Mock
    private RateSourceClient rateSourceClientMock;

    private List<RateDto> filledDtoData;
    private List<RateDto> emptyDtoData;
    private List<Rate> filledRateData;
    private List<Rate> emptyRateData;

    private Map<Integer, Currency> currencyMapSource;

    @BeforeEach
    void setUp() {

        RateDto dto1 = new RateDto();
        dto1.setRate(40.01f);
        dto1.setCode(101);
        dto1.setName("Some Strange Country Tugrik");
        dto1.setName("SST");
        dto1.setDate("05.10.2000");
        RateDto dto2 = new RateDto();
        dto2.setRate(21.99f);
        dto2.setCode(203);
        dto2.setName("Other Country Peset");
        dto2.setName("OCP");
        dto2.setDate("05.10.2000");
        filledDtoData = new ArrayList<>() {{
            add(dto1);
            add(dto2);
        }};
        Currency curr1 = Mapper.toCurrency(dto1);
        Currency curr2 = Mapper.toCurrency(dto2);
        emptyDtoData = Collections.emptyList();
        currencyMapSource = new HashMap<>() {{
            put(dto1.getCode(), curr1);
            put(dto2.getCode(), curr2);
        }};

        Rate rate1 = Mapper.toRate(dto1);
        Rate rate2 = Mapper.toRate(dto2);

        emptyRateData = Collections.emptyList();
        filledRateData = new ArrayList<>() {{
            add(rate1);
            add(rate2);
        }};
    }

    @Test
    void get_rates_for_date_happy_pass() {

        LocalDate date = LocalDate.of(2000, 10, 5);

        Mockito.when(rateSourceClientMock.readCurrencies())
                .thenReturn(currencyMapSource);

        Mockito.when(rateRepositoryMock.findByDate(date))
                .thenReturn(filledRateData);

        List<RateDto> ratesForDate = rateService.getRatesForDate(date);

        Assertions.assertNotNull(ratesForDate);
        Assertions.assertEquals(2, ratesForDate.size());
    }

    @Test
    void get_rates_for_date_not_found() {

        LocalDate date = LocalDate.of(2000, 10, 3);

        Mockito.when(rateSourceClientMock.readCurrencies())
                .thenReturn(currencyMapSource);

        Mockito.when(rateRepositoryMock.findByDate(date))
                .thenReturn(emptyRateData);

        List<RateDto> ratesForDate = rateService.getRatesForDate(date);

        Assertions.assertNotNull(ratesForDate);
        Assertions.assertEquals(0, ratesForDate.size());
    }

    @Test
    void get_rates_happy_pass() {

        LocalDate date = LocalDate.of(2000, 10, 10);

        Mockito.when(rateSourceClientMock.readCurrencies())
                .thenReturn(currencyMapSource);

        Mockito.when(rateRepositoryMock.findByDate(date))
                .thenReturn(emptyRateData);

        List<RateDto> ratesForDate = rateService.getRatesForDate(date);

        Assertions.assertNotNull(ratesForDate);
        Assertions.assertEquals(0, ratesForDate.size());
    }
}