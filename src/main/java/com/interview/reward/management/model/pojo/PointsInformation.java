package com.interview.reward.management.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.YearMonth;

@Data
@Builder
@AllArgsConstructor
public class PointsInformation {

    private YearMonth yearMonth;
    private Long bonusPoints;
}
