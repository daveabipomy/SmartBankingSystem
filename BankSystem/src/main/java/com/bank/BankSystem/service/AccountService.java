package com.bank.BankSystem.service;

import com.bank.BankSystem.model.Account;

public interface AccountService {
    public  Account createAccount(Account account);
    public Account getAccount();
}
