package com.bank.currency.rates.repo;

import com.bank.currency.rates.domain.Rate;
import com.bank.currency.rates.domain.RateId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RateRepository extends JpaRepository<Rate, RateId> {
    List<Rate> findByDate(LocalDate date);

    void deleteByDate(LocalDate date);

}
