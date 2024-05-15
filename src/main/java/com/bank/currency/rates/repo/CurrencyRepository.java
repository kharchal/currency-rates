package com.bank.currency.rates.repo;

import com.bank.currency.rates.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
