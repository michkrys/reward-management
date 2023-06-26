package com.interview.reward.management.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserPointsInformationDto {

    private final Long userId;
    private final List<PointsInformationDto> pointsInformation;
    private final Long totalBonusPoints;

}
