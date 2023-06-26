package com.interview.reward.management.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.YearMonth;

@Data
public class PointsInformationDto {

    private YearMonth yearMonth;
    private BigDecimal bonusPoints;
}
