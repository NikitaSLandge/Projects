package com.example.Farm_management.domain;

import com.example.Farm_management.domain.enumeration.TransactionType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private double quantity;
    private LocalDate date;
    private int amount;
    private String payment_status;
    private String transaction_receipt;
    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

    public Transaction() {
    }

    public Transaction(Long id, TransactionType transactionType, double quantity, LocalDate date, int amount, String payment_status, String transaction_receipt, Farm farm) {
        this.id = id;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = date;
        this.amount = amount;
        this.payment_status = payment_status;
        this.transaction_receipt = transaction_receipt;
        this.farm = farm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getTransaction_receipt() {
        return transaction_receipt;
    }

    public void setTransaction_receipt(String transaction_receipt) {
        this.transaction_receipt = transaction_receipt;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}
