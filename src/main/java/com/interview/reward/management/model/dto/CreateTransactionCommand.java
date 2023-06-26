package com.interview.reward.management.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateTransactionCommand {

    private final BigDecimal price;
    private final LocalDateTime date;
    private final Long customerId;
}
