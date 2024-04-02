package com.example.Farm_management.web.rest;

import com.example.Farm_management.Service.TransactionService;
import com.example.Farm_management.Service.dto.TransactionDto;
import com.example.Farm_management.domain.enumeration.TransactionType;
import com.example.Farm_management.repository.FarmRepository;
import com.example.Farm_management.repository.TransactionRepository;
import com.example.Farm_management.web.exception.BadRequestAlertException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TransactionResource {
    private final TransactionService transactionService;

    private final TransactionRepository transactionRepository;
    private final FarmRepository farmRepository;

    public TransactionResource(TransactionService transactionService, TransactionRepository transactionRepository, FarmRepository farmRepository) {
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
        this.farmRepository = farmRepository;
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        if (transactionDto.getId() != null) {
            throw new BadRequestAlertException("A new transaction cannot already have an id");
        }
        if (!farmRepository.existsById(transactionDto.getFarm().getId())) {
            throw new BadRequestAlertException("Farm ID does not exist");
        }
        TransactionDto result = transactionService.save(transactionDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<TransactionDto>> getAllTransactions(TransactionDto transactionDto) {
        List<TransactionDto> allTransactions = transactionService.getAllTransactions(transactionDto);
        if(allTransactions.isEmpty()){
            throw new BadRequestAlertException("no transactions present in database");
        }
        return ResponseEntity.ok().body(allTransactions);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<Optional<TransactionDto>> getTransactions(@PathVariable Long id) {
        if(!transactionRepository.existsById(id)){
            throw new BadRequestAlertException("transaction not found");
        }
        Optional<TransactionDto> transactionDto1 = transactionService.getTransactions(id);
        return ResponseEntity.ok().body(transactionDto1);
    }
    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDto>> getAllTransactions(@RequestParam(required = false) TransactionType transactionType) {
        List<TransactionDto> list;
        System.out.println("Transaction type received");
        if (transactionType != null) {
            if (transactionType == TransactionType.income) {
                list = transactionService.getAllIncomeTransactions();
            } else if (transactionType == TransactionType.income) {
                list = transactionService.getAllExpenseTransactions();
            } else {
                throw new BadRequestAlertException("Invalid transaction type. It should be 'INCOME' or 'EXPENSE'");
            }
        } else {
            list = transactionService.getAllTransactions(new TransactionDto());
        }
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        if (transactionDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        if (!transactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Transaction not found");
        }
        TransactionDto result = transactionService.update(transactionDto);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/transaction/{id}")
    public ResponseEntity<Optional<TransactionDto>> partialUpdateTransactions(
            @PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        if (transactionDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        if (!transactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Transaction not found");
        }

        Optional<TransactionDto> result = transactionService.partialUpdate(transactionDto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {
        if(!transactionRepository.existsById(id)){
            throw new BadRequestAlertException("transaction not found");
        }
        transactionService.delete(id);
        String successMessage = "Transaction with ID " + id + " has been deleted successfully.";
        return ResponseEntity.ok(successMessage);
    }
}
