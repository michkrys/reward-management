package com.interview.reward.management.factory;

import com.interview.reward.management.model.entity.Transaction;
import com.interview.reward.management.model.pojo.CalculationResult;
import com.interview.reward.management.model.pojo.PointsInformation;
import com.interview.reward.management.model.pojo.UserPointsInformation;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@Component
public class CalculationResultFactory {

    public CalculationResult createCalculationResult(List<Transaction> transactions, LocalDate startDate, LocalDate endDate) {
        MultiValueMap<Long, Transaction> customerIdToTransactionsMapping = new LinkedMultiValueMap<>();
        transactions.forEach(transaction -> customerIdToTransactionsMapping.add(transaction.getCustomer().getId(), transaction));

        List<UserPointsInformation> userPointsInformation = customerIdToTransactionsMapping.entrySet().stream()
                .map(this::createUserPointsInformation)
                .toList();

        return CalculationResult.builder()
                .startDate(startDate)
                .endDate(endDate)
                .userPointsInformation(userPointsInformation)
                .build();
    }

    UserPointsInformation createUserPointsInformation(Map.Entry<Long, List<Transaction>> customerIdToTransactionsEntry) {
        MultiValueMap<YearMonth, Transaction> monthToTransactionsMapping = new LinkedMultiValueMap<>();
        customerIdToTransactionsEntry.getValue().forEach(transaction -> monthToTransactionsMapping.add(YearMonth.of(transaction.getDate().getYear(), transaction.getDate().getMonth()), transaction));
        List<PointsInformation> pointsInformation = monthToTransactionsMapping.entrySet().stream()
                .map(entry -> createPointsInformation(entry.getValue(), entry.getKey()))
                .toList();

        return UserPointsInformation.builder()
                .userId(customerIdToTransactionsEntry.getKey())
                .pointsInformation(pointsInformation)
                .totalBonusPoints(pointsInformation.stream().map(PointsInformation::getBonusPoints).reduce(0L, Long::sum))
                .build();
    }

    PointsInformation createPointsInformation(List<Transaction> transactionsPerYearMonth, YearMonth yearMonth) {
        return PointsInformation.builder()
                .yearMonth(yearMonth)
                .bonusPoints(transactionsPerYearMonth.stream().map(Transaction::getBonusPoints).reduce(0L, Long::sum))
                .build();
    }

}
