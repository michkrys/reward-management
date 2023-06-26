package com.interview.reward.management.mapper;

import com.interview.reward.management.model.dto.CustomerDto;
import com.interview.reward.management.model.entity.Customer;
import com.interview.reward.management.model.pojo.CustomerInternal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class CustomerMapperTest {

    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    public void toPojo() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setName("John");
        customerDto.setSurname("Doe");
        customerDto.setBonusPoints(100L);

        CustomerInternal customerInternal = customerMapper.toPojo(customerDto);

        Assertions.assertEquals(customerDto.getId(), customerInternal.getId());
        Assertions.assertEquals(customerDto.getName(), customerInternal.getName());
        Assertions.assertEquals(customerDto.getSurname(), customerInternal.getSurname());
        Assertions.assertEquals(customerDto.getBonusPoints(), customerInternal.getBonusPoints());
    }

    @Test
    public void toEntity() {
        CustomerInternal customerInternal = new CustomerInternal(1L, "John", "Doe", 100L);

        Customer customer = customerMapper.toEntity(customerInternal);

        Assertions.assertEquals(customerInternal.getId(), customer.getId());
        Assertions.assertEquals(customerInternal.getName(), customer.getName());
        Assertions.assertEquals(customerInternal.getSurname(), customer.getSurname());
        Assertions.assertEquals(customerInternal.getBonusPoints(), customer.getBonusPoints());
    }

    @Test
    public void toDto() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John");
        customer.setSurname("Doe");
        customer.setBonusPoints(100L);

        CustomerDto customerDto = customerMapper.toDto(customer);

        Assertions.assertEquals(customer.getId(), customerDto.getId());
        Assertions.assertEquals(customer.getName(), customerDto.getName());
        Assertions.assertEquals(customer.getSurname(), customerDto.getSurname());
        Assertions.assertEquals(customer.getBonusPoints().intValue(), customerDto.getBonusPoints());
    }
}

