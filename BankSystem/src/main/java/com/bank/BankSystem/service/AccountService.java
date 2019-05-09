package com.bank.BankSystem.service;

import com.bank.BankSystem.model.Customer;
import org.springframework.web.multipart.MultipartFile;

public interface AccountService {
//    public int accountAuthentication(String username, String password);
    public Customer requestNewAccount(Customer customer, MultipartFile file);
}
