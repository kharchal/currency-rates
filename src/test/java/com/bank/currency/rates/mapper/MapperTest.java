package com.bank.currency.rates.mapper;

import com.bank.currency.rates.domain.Rate;
import com.bank.currency.rates.dto.RateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Test
    void to_rate_test_should_not_equals() {
        Rate expected = new Rate();
        expected.setRate(40.01f);
        expected.setCode(100);
        expected.setDate(LocalDate.of(2000, 10, 15));

        RateDto dto = new RateDto();
        dto.setDate(dateFormat.format(LocalDate.of(2000, 10, 15)));
        dto.setName("ABC");
        dto.setCode(100);
        dto.setLocalName("Abvnvn Bajdoj Cojwpw");
        dto.setRate(40.01f);

        Rate rate = Mapper.toRate(dto);
        Assertions.assertNotNull(rate);
        Assertions.assertNotEquals(expected, rate);

    }

    @Test
    void to_rate_test_should_equals() {
        Rate expected = new Rate();
        expected.setRate(40.01f);
        expected.setCode(100);
        expected.setDate(LocalDate.of(2000, 10, 15));

        RateDto dto = new RateDto();
        dto.setDate(dateFormat.format(LocalDate.of(2000, 10, 15)));
        dto.setName("ABC");
        dto.setCode(100);
        dto.setLocalName("Abvnvn Bajdoj Cojwpw");
        dto.setRate(40.01f);

        Rate rate = Mapper.toRate(dto);
        Assertions.assertNotNull(rate);
        Assertions.assertEquals(expected.getRate(), rate.getRate());
        Assertions.assertEquals(expected.getCode(), rate.getCode());
        Assertions.assertEquals(expected.getDate(), rate.getDate());

    }

    @Test
    void to_rate_test_should_throw() {
        Rate expected = new Rate();
        expected.setRate(40.01f);
        expected.setCode(100);
        expected.setDate(LocalDate.of(2000, 10, 15));

        RateDto dto = new RateDto();
        dto.setDate("2020-10-10");
        dto.setName("ABC");
        dto.setCode(100);
        dto.setLocalName("Abvnvn Bajdoj Cojwpw");
        dto.setRate(40.01f);

        Assertions.assertThrows(RuntimeException.class, () -> Mapper.toRate(dto));

    }
}