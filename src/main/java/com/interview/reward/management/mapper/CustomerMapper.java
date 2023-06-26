package com.interview.reward.management.mapper;

import com.interview.reward.management.model.dto.CustomerDto;
import com.interview.reward.management.model.entity.Customer;
import com.interview.reward.management.model.pojo.CustomerInternal;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomerMapper {
    CustomerInternal toPojo(CustomerDto customerDto);

    Customer toEntity(CustomerInternal customerInternal);

    CustomerDto toDto(Customer customer);
}
