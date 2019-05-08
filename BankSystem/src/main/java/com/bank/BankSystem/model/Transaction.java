package com.bank.BankSystem.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int transactionId;
    private String transactionType;
    private LocalDate transactionDate;
    private double amount;
    @OneToOne
    private NewAccount fromWho;
    @OneToOne
//    @Column(name = "towho",nullable = true)
    private NewAccount toWho;


    @ManyToOne(targetEntity = NewAccount.class)
    private NewAccount account;
    @ManyToOne(targetEntity = Teller.class)
    private Teller teller;
}
