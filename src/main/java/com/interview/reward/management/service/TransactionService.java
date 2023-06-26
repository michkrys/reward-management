package com.interview.reward.management.service;

import com.interview.reward.management.exception.CustomerException;
import com.interview.reward.management.exception.TransactionException;
import com.interview.reward.management.mapper.TransactionMapper;
import com.interview.reward.management.model.dto.TransactionDto;
import com.interview.reward.management.model.entity.Customer;
import com.interview.reward.management.model.entity.Transaction;
import com.interview.reward.management.model.pojo.CreateTransactionInternalCommand;
import com.interview.reward.management.model.pojo.UpdateTransactionInternalCommand;
import com.interview.reward.management.processor.BonusPointsProcessor;
import com.interview.reward.management.repository.CustomerRepository;
import com.interview.reward.management.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.interview.reward.management.common.ExceptionMessage.notFoundMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;
    private final TransactionMapper transactionMapper;
    private final BonusPointsProcessor bonusPointsProcessor;

    @Transactional
    public TransactionDto createTransaction(CreateTransactionInternalCommand createTransactionInternalCommand) {
        Transaction transaction = transactionMapper.toEntity(createTransactionInternalCommand);
        transaction.setBonusPoints(bonusPointsProcessor.calculate(transaction));
        transactionRepository.save(transaction);
        return transactionMapper.toDto(transaction);
    }

    public TransactionDto getTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionException(notFoundMessage("Transaction"), HttpStatus.NOT_FOUND));
        return transactionMapper.toDto(transaction);
    }

    @Transactional
    public TransactionDto updateTransaction(Long transactionId, UpdateTransactionInternalCommand command) {
        log.info("Starting the transaction with id '{}' update process.", transactionId);
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionException(notFoundMessage("Transaction"), HttpStatus.NOT_FOUND));
        Customer customer = customerRepository.findById(command.getCustomerId())
                .orElseThrow(() -> new CustomerException(notFoundMessage("Customer"), HttpStatus.NOT_FOUND));
        transaction.update(command.getPrice(), command.getDate(), customer);
        transactionRepository.save(transaction);
        log.info("Completion of transaction with id '{}' update process.", transactionId);
        return transactionMapper.toDto(transaction);
    }

    public TransactionDto deleteTransaction(Long transactionId) {
        log.info("Starting the transaction with id '{}' delete process.", transactionId);
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionException(notFoundMessage("Transaction"), HttpStatus.NOT_FOUND));
        transactionRepository.delete(transaction);
        log.info("Completion of transaction with id '{}' delete process.", transactionId);
        return transactionMapper.toDto(transaction);
    }
}
