package com.bank.BankSystem.dao;

import com.bank.BankSystem.model.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login,Long> {
}
