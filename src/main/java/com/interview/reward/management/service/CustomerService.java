package com.interview.reward.management.service;

import com.interview.reward.management.exception.CustomerException;
import com.interview.reward.management.mapper.CustomerMapper;
import com.interview.reward.management.model.dto.CustomerDto;
import com.interview.reward.management.model.entity.Customer;
import com.interview.reward.management.model.pojo.CustomerInternal;
import com.interview.reward.management.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.interview.reward.management.common.ExceptionMessage.notFoundMessage;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public CustomerDto createCustomer(CustomerInternal customerInternal) {
        Customer customer = customerMapper.toEntity(customerInternal);
        customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    public CustomerDto getCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerException(notFoundMessage("Customer"), HttpStatus.NOT_FOUND));
        return customerMapper.toDto(customer);
    }

    @Transactional
    public CustomerDto updateCustomer(Long customerId, CustomerInternal customerInternal) {
        log.info("Starting the customer with id '{}' update process.", customerId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerException(notFoundMessage("Customer"), HttpStatus.NOT_FOUND));
        customer.update(customerInternal.getName(), customerInternal.getSurname(), customerInternal.getBonusPoints());
        customerRepository.save(customer);
        log.info("Completion of customer with id '{}' update process.", customerId);
        return customerMapper.toDto(customer);
    }

    public CustomerDto deleteCustomer(Long customerId) {
        log.info("Starting the customer with id '{}' delete process.", customerId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerException(notFoundMessage("Customer"), HttpStatus.NOT_FOUND));
        customerRepository.delete(customer);
        log.info("Completion of customer with id '{}' delete process.", customerId);
        return customerMapper.toDto(customer);
    }
}
