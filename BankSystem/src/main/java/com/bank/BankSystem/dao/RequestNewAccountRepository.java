package com.bank.BankSystem.dao;

import com.bank.BankSystem.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestNewAccountRepository extends CrudRepository<Customer,Long> {
}
