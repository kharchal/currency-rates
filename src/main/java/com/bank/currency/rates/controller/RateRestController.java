package com.bank.currency.rates.controller;

import com.bank.currency.rates.dto.RateDto;
import com.bank.currency.rates.service.RateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("rates")
public class RateRestController {

    private final RateService rateService;

    @GetMapping("fortoday")
    public ResponseEntity<List<RateDto>> todaysRates() {
        List<RateDto> list = rateService.getRates();
        log.info("Response was sent: {}", list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("fordate/{date}")
    public ResponseEntity<List<RateDto>> forDateRates(@PathVariable LocalDate date) {
        List<RateDto> list = rateService.getRatesForDate(date);
        log.info("Response was sent: {}", list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("delete/{date}")
    public ResponseEntity<List<RateDto>> deleteRatesForDate(@PathVariable LocalDate date) {
        List<RateDto> list = rateService.deleteForDate(date);
        log.info("Rates for {} were deleted", date);
        return ResponseEntity.ok(list);
    }

//    @GetMapping("test")
//    public ResponseEntity test() {
//        return ResponseEntity.ok("ok message");
//    }
//
//    @GetMapping("error")
//    public ResponseEntity error() {
//        if (true) throw new RuntimeException("something went wrong...");
//        return ResponseEntity.ok("error message");
//
//    }
}
