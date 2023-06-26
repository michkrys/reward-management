package com.interview.reward.management.factory;

import com.interview.reward.management.model.entity.Customer;
import com.interview.reward.management.model.entity.Transaction;
import com.interview.reward.management.model.pojo.CalculationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalculationResultFactoryTest {

    private CalculationResultFactory calculationResultFactory;

    @BeforeEach
    public void setup() {
        calculationResultFactory = new CalculationResultFactory();
    }

    @Test
    public void createCalculationResult() {
        List<Transaction> transactions = new ArrayList<>();
        Customer customer1 = new Customer(1L, "John", "Doe", 100L);
        Customer customer2 = new Customer(2L, "Jane", "Smith", 200L);

        Transaction transaction1 = new Transaction(1L, new BigDecimal("50.00"), LocalDateTime.of(2022, 10, 1, 1, 1), customer1, 10L);
        Transaction transaction2 = new Transaction(2L, new BigDecimal("100.00"), LocalDateTime.of(2022, 11, 1, 1, 1), customer1, 20L);
        Transaction transaction3 = new Transaction(2L, new BigDecimal("100.00"), LocalDateTime.of(2022, 11, 1, 1, 1), customer1, 20L);
        Transaction transaction4 = new Transaction(3L, new BigDecimal("75.00"), LocalDateTime.of(2022, 12, 1, 1, 1), customer2, 15L);

        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);

        LocalDate startDate = LocalDate.of(2022, 9, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 1);

        CalculationResult result = calculationResultFactory.createCalculationResult(transactions, startDate, endDate);

        Assertions.assertEquals(startDate, result.getStartDate());
        Assertions.assertEquals(endDate, result.getEndDate());
        Assertions.assertEquals(2, result.getUserPointsInformation().size());
        Assertions.assertEquals(1L, result.getUserPointsInformation().get(0).getUserId());
        Assertions.assertEquals(2, result.getUserPointsInformation().get(0).getPointsInformation().size());
        Assertions.assertEquals(50L, result.getUserPointsInformation().get(0).getTotalBonusPoints());
        Assertions.assertEquals(40L, result.getUserPointsInformation().get(0).getPointsInformation().get(1).getBonusPoints());
        Assertions.assertEquals(2L, result.getUserPointsInformation().get(1).getUserId());
        Assertions.assertEquals(1, result.getUserPointsInformation().get(1).getPointsInformation().size());
        Assertions.assertEquals(15L, result.getUserPointsInformation().get(1).getTotalBonusPoints());
    }

}

