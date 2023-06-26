package com.interview.reward.management.service;

import com.interview.reward.management.factory.CalculationResultFactory;
import com.interview.reward.management.mapper.CalculationMapper;
import com.interview.reward.management.model.dto.CalculationResultDto;
import com.interview.reward.management.model.pojo.CalculationResult;
import com.interview.reward.management.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class RewardService {
    private final TransactionRepository transactionRepository;
    private final CalculationResultFactory calculationResultFactory;
    private final CalculationMapper calculationMapper;

    public CalculationResultDto calculate(LocalDate startDate, LocalDate endDate) {
        log.info("Process of calculating transaction details started.");
        if (startDate == null || endDate == null) {
            log.info("Time range was not provided. Transactions for last 3 months will be calculated.");
            endDate = LocalDate.now();
            startDate = endDate.minusMonths(3);
        }
        var transactions = transactionRepository.findByDateBetweenAndBonusPointsGreaterThan(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(0, 0)), BigDecimal.ZERO);
        CalculationResult calculationResult = calculationResultFactory.createCalculationResult(transactions.stream().toList(), startDate, endDate);
        log.info("Process of calculating transaction details ended.");
        return calculationMapper.toDto(calculationResult);
    }

}
