package com.interview.reward.management.exception;

import org.springframework.http.HttpStatus;

public class CustomerException extends RewardManagementException {

    public CustomerException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
