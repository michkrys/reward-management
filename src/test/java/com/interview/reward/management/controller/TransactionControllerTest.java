package com.interview.reward.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.reward.management.model.dto.CreateTransactionCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AutoConfigureMockMvc
@SpringBootTest
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @Rollback
    public void createTransaction() throws Exception {
        CreateTransactionCommand createTransactionCommand = new CreateTransactionCommand(
                BigDecimal.valueOf(100),
                LocalDateTime.now(),
                1L
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTransactionCommand)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void getTransaction() throws Exception {
        Long transactionId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/{transactionId}", transactionId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(10.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2023-05-01T10:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(1));
    }

    @Test
    @Transactional
    @Rollback
    public void updateTransaction() throws Exception {
        Long transactionId = 1L;
        CreateTransactionCommand updateTransactionCommand = new CreateTransactionCommand(
                BigDecimal.valueOf(200),
                LocalDateTime.now(),
                2L
        );

        mockMvc.perform(MockMvcRequestBuilders.put("/transactions/{transactionId}", transactionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateTransactionCommand)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void deleteTransaction() throws Exception {
        Long transactionId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/transactions/{transactionId}", transactionId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
