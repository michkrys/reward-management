package com.interview.reward.management.exception;

import org.springframework.http.HttpStatus;

public class TransactionException extends RewardManagementException {

    public TransactionException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
