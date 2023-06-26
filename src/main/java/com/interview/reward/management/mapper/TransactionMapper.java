package com.interview.reward.management.mapper;

import com.interview.reward.management.model.dto.CreateTransactionCommand;
import com.interview.reward.management.model.dto.TransactionDto;
import com.interview.reward.management.model.dto.UpdateTransactionCommand;
import com.interview.reward.management.model.entity.Transaction;
import com.interview.reward.management.model.pojo.CreateTransactionInternalCommand;
import com.interview.reward.management.model.pojo.UpdateTransactionInternalCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "customer.id", target = "customerId")
    TransactionDto toDto(Transaction transaction);

    CreateTransactionInternalCommand toPojo(CreateTransactionCommand command);

    UpdateTransactionInternalCommand toPojo(UpdateTransactionCommand command);

    Transaction toEntity(CreateTransactionInternalCommand command);

}
