package com.bank.BankSystem.service.impl;

import com.bank.BankSystem.dao.AccountRepository;
import com.bank.BankSystem.model.Account;
import com.bank.BankSystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public  Account createAccount(Account account)
    {
        return  accountRepository.save(account);
    }

    public Account getAccount()
    {
//        return  accountRepository.findAll();
        return  null;
    }
}
