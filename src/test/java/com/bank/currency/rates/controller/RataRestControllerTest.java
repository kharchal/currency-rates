package com.bank.currency.rates.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("mock")
public class RataRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_get_rates_for_today_200() throws Exception {
        mockMvc.perform(get("http://localhost:8080/rates/fortoday")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test_get_rates_for_today_404() throws Exception {
        mockMvc.perform(get("http://localhost:8080/rates/forto")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_get_rates_for_date_200() throws Exception {
        mockMvc.perform(get("http://localhost:8080/rates/fordate/2024-05-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test_get_rates_for_date_400() throws Exception {
        mockMvc.perform(get("http://localhost:8080/rates/fordate/2024-05-01-21")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_delete_by_get_rates_for_date_200() throws Exception {
        mockMvc.perform(get("http://localhost:8080/rates/delete/2024-05-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test_delete_by_get_rates_for_date_400() throws Exception {
        mockMvc.perform(get("http://localhost:8080/rates/delete/2024-05-01-03")
                        .contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(status().is4xxClientError());
    }
}
