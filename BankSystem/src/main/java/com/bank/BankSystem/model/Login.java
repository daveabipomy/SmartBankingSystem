package com.bank.BankSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {
    @Id
    private String userName;
    private String password;
}
