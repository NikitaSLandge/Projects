package com.example.Farm_management.repository;

import com.example.Farm_management.Service.dto.TransactionDto;
import com.example.Farm_management.domain.Transaction;
import com.example.Farm_management.domain.enumeration.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByTransactionType( TransactionType transactionType);
}
