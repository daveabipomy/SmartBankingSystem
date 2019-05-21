package com.bank.BankSystem.service;

public interface TransactionService {

    public void deposit(String accountNumber, double amount);
    public boolean withdraw(String accountNumber, double amount);
    public void transfer(String accountNumber, String toAccountNumber, double amount);
}
