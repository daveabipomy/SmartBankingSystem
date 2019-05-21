package com.bank.BankSystem.dao;

import com.bank.BankSystem.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
