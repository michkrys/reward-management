package com.interview.reward.management.controller;

import com.interview.reward.management.mapper.CustomerMapper;
import com.interview.reward.management.model.dto.CustomerDto;
import com.interview.reward.management.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    @Operation(summary = "Create customer", tags = "Customer")
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.createCustomer(customerMapper.toPojo(customerDto));
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "Get information about customer", tags = "Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDto.class))}),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))})
    })
    public CustomerDto getCustomer(@PathVariable("customerId") Long customerId) {
        return customerService.getCustomer(customerId);
    }

    @PutMapping("/{transactionId}")
    @Operation(summary = "Update customer", tags = "Customer")
    public CustomerDto updateCustomer(@PathVariable("customerId") Long customerId, @RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(customerId, customerMapper.toPojo(customerDto));
    }

    @DeleteMapping("/{transactionId}")
    @Operation(summary = "Delete customer", tags = "Customer")
    public CustomerDto deleteTransaction(@PathVariable("customerId") Long customerId) {
        return customerService.deleteCustomer(customerId);
    }

}
