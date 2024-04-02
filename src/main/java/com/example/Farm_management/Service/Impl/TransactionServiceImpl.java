package com.example.Farm_management.Service.Impl;

import com.example.Farm_management.Service.TransactionService;
import com.example.Farm_management.Service.dto.TransactionDto;
import com.example.Farm_management.Service.mapper.TransactionMapper;
import com.example.Farm_management.domain.Transaction;
import com.example.Farm_management.domain.enumeration.TransactionType;
import com.example.Farm_management.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionDto save(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        transaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(transaction);
    }



    @Override
    public Optional<TransactionDto> getTransactions(Long id) {
        return transactionRepository.findById(id).map(transactionMapper::toDto);
    }

    @Override
    public TransactionDto update(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        transaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(transaction);
    }

    @Override
    public Optional<TransactionDto> partialUpdate(TransactionDto transactionDto) {
        return transactionRepository.findById(transactionDto.getId()).map(existingTransactions -> {
            transactionMapper.partialUpdate(existingTransactions, transactionDto);
            return existingTransactions;
        }).map(transactionRepository::save).map(transactionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        transactionRepository.deleteById(id);

    }
    @Override
    public List<TransactionDto> getAllTransactions(TransactionDto transactionDto) {
        List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
        return transactionMapper.toDto(transactions);
    }

    @Override
    @Transactional
    public List<TransactionDto> getAllIncomeTransactions() {
        List<Transaction> allIncomeTransactions =  transactionRepository.findByTransactionType(TransactionType.income);
         return transactionMapper.toDto(allIncomeTransactions);
    }

    @Override
    @Transactional
    public List<TransactionDto> getAllExpenseTransactions() {
        List<Transaction> allExpenseTransactions = transactionRepository.findByTransactionType(TransactionType.expense);
        return transactionMapper.toDto(allExpenseTransactions);
    }
}
