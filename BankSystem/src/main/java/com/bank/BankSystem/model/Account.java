package com.bank.BankSystem.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.*;
//import sun.util.resources.Bundles;

import javax.persistence.*;
import java.time.temporal.ValueRange;

@Entity
@Data
@Getter @Setter
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int Id;
    @Column(name = "username",nullable = false, unique = true)
    @Setter(AccessLevel.PACKAGE)
    public String name;
    @Column(name = "email",nullable = false, unique = true)
    public String email;
    public String password;

    public  Account(String name, String email,String password)
    {
        this.name=name;
        this.email=email;
        this.password=password;
    }
}
