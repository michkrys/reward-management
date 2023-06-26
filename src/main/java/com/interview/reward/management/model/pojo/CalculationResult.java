package com.interview.reward.management.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CalculationResult {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<UserPointsInformation> userPointsInformation;

}
