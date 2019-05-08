package com.bank.BankSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

//@MappedSuperclass
@MappedSuperclass
public class Employee {
    @Id
    private int empid;
    private String firstName;
    private String lastName;
    private LocalDate DOB;
    private LocalDate joiningDate;
    public  int ssn;

}
