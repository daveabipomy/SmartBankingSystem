package com.bank.BankSystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int Id;
    @Column(name = "FirstName",nullable = false)
    private String firstName;

    @Column(name = "LastName",nullable = false)
    private String lastName;
    private int age;
    private String ssn;
    private String phone;
    private String Email;
    private String sex;
    private boolean approval;
    private String photo;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE,orphanRemoval = true)
    @JsonManagedReference(value="customer-account")
    private List<NewAccount> accounts;

    @OneToOne
    private Login login;

    @OneToOne
    private Address address;





}
