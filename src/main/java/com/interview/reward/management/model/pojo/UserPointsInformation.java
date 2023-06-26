package com.interview.reward.management.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserPointsInformation {

    private Long userId;
    private List<PointsInformation> pointsInformation;
    private Long totalBonusPoints;

}
