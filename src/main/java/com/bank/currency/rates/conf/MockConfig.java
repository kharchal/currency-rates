package com.bank.currency.rates.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mock")
public class MockConfig {

    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
