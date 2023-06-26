package com.interview.reward.management.repository;

import com.interview.reward.management.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Set<Transaction> findByDateBetweenAndBonusPointsGreaterThan(LocalDateTime startDate, LocalDateTime endDate, BigDecimal bonusPoints);
}
