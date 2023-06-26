package com.interview.reward.management.mapper;

import com.interview.reward.management.model.dto.CalculationResultDto;
import com.interview.reward.management.model.dto.PointsInformationDto;
import com.interview.reward.management.model.dto.UserPointsInformationDto;
import com.interview.reward.management.model.pojo.CalculationResult;
import com.interview.reward.management.model.pojo.PointsInformation;
import com.interview.reward.management.model.pojo.UserPointsInformation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CalculationMapper {

    CalculationResultDto toDto(CalculationResult calculationResult);

    PointsInformationDto toDto(PointsInformation pointsInformation);

    UserPointsInformationDto toDto(UserPointsInformation calculationResult);

}
