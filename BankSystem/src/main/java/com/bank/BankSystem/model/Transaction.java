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


//    @ManyToOne
    private String fromWho;

//    @ManyToOne
    private String toWho;


    @ManyToOne(targetEntity = NewAccount.class)
    private NewAccount account;
    @ManyToOne(targetEntity = Teller.class)
    private Teller teller;
}
