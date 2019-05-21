package com.bank.BankSystem.dao;

import com.bank.BankSystem.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepostitory extends CrudRepository<Account, Long> {

    public Account findByAccountNumber(String accountNumber);


}
