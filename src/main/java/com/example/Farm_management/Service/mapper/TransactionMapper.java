package com.example.Farm_management.Service.mapper;

import com.example.Farm_management.domain.Transaction;
import com.example.Farm_management.Service.dto.TransactionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends Entitymapper<TransactionDto, Transaction>  {

    TransactionDto toDto(Transaction transaction);

    List<TransactionDto> toDto(List<Transaction> transactions);

    Transaction toEntity(TransactionDto transactionDto);
}
