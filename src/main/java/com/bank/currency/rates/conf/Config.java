package com.bank.currency.rates.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
@EnableTransactionManagement
@Slf4j
public class Config {

    @Bean
    public CommonsRequestLoggingFilter loggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setBeforeMessagePrefix("### BEFORE REQUEST: ");
        filter.setAfterMessagePrefix("### AFTER REQUEST: ");
        log.info("Logging filter was initialised");
        return filter;
    }


}
