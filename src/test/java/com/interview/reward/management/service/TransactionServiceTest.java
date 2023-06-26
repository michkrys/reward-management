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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @Mock
    private BonusPointsProcessor bonusPointsProcessor;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void createTransaction_DataCorrect_TransactionCreated() {
        CreateTransactionInternalCommand command = new CreateTransactionInternalCommand();
        Transaction transaction = new Transaction();
        TransactionDto expectedDto = new TransactionDto(1L, BigDecimal.valueOf(100), LocalDateTime.of(1999, 12, 12, 1, 1), 1L);

        Mockito.when(transactionMapper.toEntity(command)).thenReturn(transaction);
        Mockito.when(bonusPointsProcessor.calculate(transaction)).thenReturn(10L);
        Mockito.when(transactionRepository.save(transaction)).thenReturn(transaction);
        Mockito.when(transactionMapper.toDto(transaction)).thenReturn(expectedDto);

        TransactionDto result = transactionService.createTransaction(command);

        Assertions.assertEquals(expectedDto, result);
        Mockito.verify(transactionRepository, Mockito.times(1)).save(transaction);
    }

    @Test
    public void getTransaction_DataCorrect_TransactionReturned() {
        Long transactionId = 1L;
        Transaction transaction = new Transaction();
        TransactionDto expectedDto = new TransactionDto(1L, BigDecimal.valueOf(100), LocalDateTime.of(1999, 12, 12, 1, 1), 1L);

        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));
        Mockito.when(transactionMapper.toDto(transaction)).thenReturn(expectedDto);

        TransactionDto result = transactionService.getTransaction(transactionId);

        Assertions.assertEquals(expectedDto, result);
    }

    @Test
    public void getTransaction_ThrowsTransactionException_WhenTransactionNotFound() {
        Long transactionId = 1L;
        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        Assertions.assertThrows(TransactionException.class, () -> transactionService.getTransaction(transactionId));
    }

    @Test
    public void updateTransaction_DataCorrect_TransactionUpdated() {
        Long transactionId = 1L;
        UpdateTransactionInternalCommand command = new UpdateTransactionInternalCommand(BigDecimal.valueOf(40), LocalDateTime.of(1999, 12, 12, 1, 1), 1L);
        Transaction transaction = new Transaction();
        Customer customer = new Customer();
        TransactionDto expectedDto = new TransactionDto(1L, BigDecimal.valueOf(100), LocalDateTime.of(1999, 12, 12, 1, 1), 1L);

        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));
        Mockito.when(customerRepository.findById(command.getCustomerId())).thenReturn(Optional.of(customer));
        Mockito.when(transactionRepository.save(transaction)).thenReturn(transaction);
        Mockito.when(transactionMapper.toDto(transaction)).thenReturn(expectedDto);

        TransactionDto result = transactionService.updateTransaction(transactionId, command);

        Assertions.assertEquals(expectedDto, result);
        Mockito.verify(transactionRepository, Mockito.times(1)).save(transaction);
    }

    @Test
    public void updateTransaction_ThrowsTransactionException_WhenTransactionNotFound() {
        Long transactionId = 1L;
        UpdateTransactionInternalCommand command = new UpdateTransactionInternalCommand(BigDecimal.valueOf(40), LocalDateTime.of(1999, 12, 12, 1, 1), 1L);

        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        Assertions.assertThrows(TransactionException.class, () -> transactionService.updateTransaction(transactionId, command));
    }

    @Test
    public void updateTransaction_ThrowsCustomerException_WhenCustomerNotFound() {
        Long transactionId = 1L;
        UpdateTransactionInternalCommand command = new UpdateTransactionInternalCommand(BigDecimal.valueOf(40), LocalDateTime.of(1999, 12, 12, 1, 1), 1L);
        Transaction transaction = new Transaction();

        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));
        Mockito.when(customerRepository.findById(command.getCustomerId())).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomerException.class, () -> transactionService.updateTransaction(transactionId, command));
    }

    @Test
    public void deleteTransaction_DataCorrect_Deleted() {
        Long transactionId = 1L;
        Transaction transaction = new Transaction();
        TransactionDto expectedDto = new TransactionDto(1L, BigDecimal.valueOf(100), LocalDateTime.of(1999, 12, 12, 1, 1), 1L);

        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));
        Mockito.when(transactionMapper.toDto(transaction)).thenReturn(expectedDto);

        TransactionDto result = transactionService.deleteTransaction(transactionId);

        Assertions.assertEquals(expectedDto, result);
        Mockito.verify(transactionRepository, Mockito.times(1)).delete(transaction);
    }

    @Test
    public void deleteTransaction_ThrowsTransactionException_WhenTransactionNotFound() {
        Long transactionId = 1L;

        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        Assertions.assertThrows(TransactionException.class, () -> transactionService.deleteTransaction(transactionId));
    }
}

