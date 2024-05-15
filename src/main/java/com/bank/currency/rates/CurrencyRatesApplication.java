package com.bank.currency.rates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class CurrencyRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyRatesApplication.class, args);
	}

}
