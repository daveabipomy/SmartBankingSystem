package com.bank.BankSystem.controller;


import com.bank.BankSystem.dao.AccountRepository;
import com.bank.BankSystem.model.Account;
import com.bank.BankSystem.service.impl.AccountServiceImp;
//import jdk.vm.ci.meta.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/smart")
public class AccountController {

    @Autowired
    AccountServiceImp accountService;

    @Autowired
    AccountRepository accountRepository;


    @RequestMapping("/account")
    public String showSignUpForm(Account account) {
        return "account";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String root() {
        return "index";
    }


    @RequestMapping(value="/accountList")
    public String accountList(Model model) {
        model.addAttribute("accountList", accountRepository.findAll());
        return "accountList";
    }







    //    @PostMapping("/account/create")
    @RequestMapping(value={"/account"},method = RequestMethod.POST)
//    public Account createAccount(@RequestParam("userName") String name,@RequestParam("email") String email, @RequestParam("password") String password)
    public String createAccount(@Valid Account account, BindingResult result, Model model)
    {
            Account accountnew=new Account(account.name,account.email,account.password);
            accountService.createAccount(accountnew);
//        return "Hello " +account.name+", Welcome to Smart bank";
        return "account";
    }


    @RequestMapping(value = {"/login"},method=RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password)
    {

        int count= accountService.accountAuthentication(username,password);

        if(count>0)
        {
            return "home";
        }
        else
        {
            return "login";
        }

    }




}
