package com.bank.BankSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="newaccount")
public class NewAccount {
    @Id
    private String accountNumber;
    private String accountType;
    private LocalDate openeingDate;
    private  LocalDate closingDate;
    private  String status;
    private  double flowLimit;
    private double balance;

    @ManyToOne(targetEntity = Customer.class)
//    @JsonBackReference
    private Customer customer;

    @ManyToOne(targetEntity = Branch.class)
//    @JsonBackReference
    private  Branch branch;

    @ManyToOne(targetEntity = Card.class)
//    @JsonBackReference
    private Card card;

    @OneToMany(mappedBy = "account",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch=FetchType.LAZY)
    @JsonManagedReference(value="transaction-account")
//    @Column(name = "transaction",nullable = false)
    private List<Transaction> transactions;

}
