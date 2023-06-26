package com.interview.reward.management.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CalculationResultDto {

    private LocalDate startDate;
    private LocalDate endDate;
    private List<UserPointsInformationDto> userPointsInformation;

}
