package com.interview.reward.management.model.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UpdateTransactionInternalCommand {
    private BigDecimal price;
    private LocalDateTime date;
    private Long customerId;
}
