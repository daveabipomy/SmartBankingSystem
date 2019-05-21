package com.bank.BankSystem.service;

import com.bank.BankSystem.model.Customer;
import com.bank.BankSystem.model.Account;
import com.bank.BankSystem.model.Login;
import org.springframework.web.multipart.MultipartFile;

public interface AccountService {
//    public int accountAuthentication(String username, String password);
    public Customer requestNewAccount(Customer customer, MultipartFile file);
    public Account findAccount(String accountNumber);
    public Account updateBalance(Account account);
	public Login register(Login login, String accountnumber);
}
