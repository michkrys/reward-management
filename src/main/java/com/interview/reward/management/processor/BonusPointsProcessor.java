package com.interview.reward.management.processor;

import com.interview.reward.management.model.entity.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class BonusPointsProcessor {

    private BonusPointsProcessorConfiguration bonusPointsProcessorConfiguration;

    public Long calculate(Transaction transaction) {
        BigDecimal transactionValueStage1 = bonusPointsProcessorConfiguration.getTransactionValueStage1();
        BigDecimal transactionValueStage2 = bonusPointsProcessorConfiguration.getTransactionValueStage2();
        BigDecimal stage2Multiplier = bonusPointsProcessorConfiguration.getStage2Multiplier();
        BigDecimal price = transaction.getPrice();
        if (transaction.getPrice() == null || transaction.getPrice().compareTo(transactionValueStage1) < 0) {
            return 0L;
        }
        BigDecimal bonusPoints = BigDecimal.ZERO;

        if (price.compareTo(transactionValueStage1) >= 0 && price.compareTo(transactionValueStage2) < 0) {
            bonusPoints = bonusPoints.add(transactionValueStage1).subtract(transactionValueStage2).add(price);
        }

        if (price.compareTo(transactionValueStage2) >= 0) {
            bonusPoints = bonusPoints.add(price.subtract(transactionValueStage2).multiply(stage2Multiplier))
                    .add(transactionValueStage2.subtract(transactionValueStage1));
        }
        return bonusPoints.longValue();
    }
}
