package com.interview.reward.management.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UpdateTransactionCommand {
    private BigDecimal price;
    private LocalDateTime date;
    private Long customerId;
}
