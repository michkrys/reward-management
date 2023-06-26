package com.interview.reward.management.handler;

import com.interview.reward.management.exception.CustomerException;
import com.interview.reward.management.exception.RewardManagementException;
import com.interview.reward.management.exception.TransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<String> customerExceptionErrorResponse(CustomerException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body("Incorrect login.");
    }

    @ExceptionHandler(RewardManagementException.class)
    public ResponseEntity<String> rewardManagementErrorResponse(RewardManagementException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body("Incorrect login.");
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<String> transactionExceptionErrorResponse(TransactionException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> generalErrorResponse(RuntimeException e) {
        log.error(e.getMessage());
        return ResponseEntity.internalServerError().body("Some unexpected error occurred. Please try again later.");
    }

}
