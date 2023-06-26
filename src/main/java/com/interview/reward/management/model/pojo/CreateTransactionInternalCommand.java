package com.interview.reward.management.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionInternalCommand {
    private BigDecimal price;
    private LocalDateTime date;
    private Long customerId;
}
