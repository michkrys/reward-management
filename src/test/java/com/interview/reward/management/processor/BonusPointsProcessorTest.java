package com.interview.reward.management.processor;

import com.interview.reward.management.model.entity.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class BonusPointsProcessorTest {

    BonusPointsProcessor bonusPointsProcessor;

    @BeforeEach
    void setUp() {
        BonusPointsProcessorConfiguration bonusPointsProcessorConfiguration = new BonusPointsProcessorConfiguration();
        bonusPointsProcessorConfiguration.setStage2Multiplier(BigDecimal.valueOf(2L));
        bonusPointsProcessorConfiguration.setTransactionValueStage1(BigDecimal.valueOf(50));
        bonusPointsProcessorConfiguration.setTransactionValueStage2(BigDecimal.valueOf(100));
        this.bonusPointsProcessor = new BonusPointsProcessor(bonusPointsProcessorConfiguration);
    }

    @ParameterizedTest
    @MethodSource
    public void calculate(BigDecimal price, Long expectedNumberOfPoints) {
        Transaction transaction = new Transaction();
        transaction.setPrice(price);

        var result = bonusPointsProcessor.calculate(transaction);

        Assertions.assertEquals(expectedNumberOfPoints, result);
    }

    public static Stream<Arguments> calculate() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(120), 90L),
                Arguments.of(BigDecimal.valueOf(100), 50L),
                Arguments.of(BigDecimal.valueOf(90), 40L),
                Arguments.of(BigDecimal.valueOf(49), 0L),
                Arguments.of(BigDecimal.valueOf(101), 52L)
        );
    }
}
