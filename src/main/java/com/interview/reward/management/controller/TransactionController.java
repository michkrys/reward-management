package com.interview.reward.management.controller;

import com.interview.reward.management.mapper.TransactionMapper;
import com.interview.reward.management.model.dto.CreateTransactionCommand;
import com.interview.reward.management.model.dto.TransactionDto;
import com.interview.reward.management.model.dto.UpdateTransactionCommand;
import com.interview.reward.management.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @PostMapping
    public TransactionDto createTransaction(@RequestBody CreateTransactionCommand createTransactionCommand) {
        return transactionService.createTransaction(transactionMapper.toPojo(createTransactionCommand));
    }

    @GetMapping("/{transactionId}")
    public TransactionDto getTransaction(@PathVariable("transactionId") Long transactionId) {
        return transactionService.getTransaction(transactionId);
    }

    @PutMapping("/{transactionId}")
    public TransactionDto updateTransaction(@PathVariable("transactionId") Long transactionId, @RequestBody UpdateTransactionCommand command) {
        return transactionService.updateTransaction(transactionId, transactionMapper.toPojo(command));
    }

    @DeleteMapping("/{transactionId}")
    public TransactionDto deleteTransaction(@PathVariable("transactionId") Long transactionId) {
        return transactionService.deleteTransaction(transactionId);
    }

}
