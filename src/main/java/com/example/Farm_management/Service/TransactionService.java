package com.example.Farm_management.Service;

import com.example.Farm_management.Service.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TransactionService {
    TransactionDto save(TransactionDto transactionDto);

    List<TransactionDto> getAllTransactions(TransactionDto transactionDto);

    Optional<TransactionDto> getTransactions(Long id);

    TransactionDto update(TransactionDto transactionDto);

    Optional<TransactionDto> partialUpdate(TransactionDto transactionDto);

    void delete(Long id);

    List<TransactionDto> getAllIncomeTransactions();


    List<TransactionDto> getAllExpenseTransactions();

}
