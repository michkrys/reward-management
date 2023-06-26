package com.interview.reward.management.model.pojo;

import com.interview.reward.management.model.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionInternal {

    private Long id;
    private BigDecimal price;
    private LocalDateTime date;
    private Customer customer;
}
