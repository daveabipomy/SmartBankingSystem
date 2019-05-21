package com.bank.BankSystem.controller;

import com.bank.BankSystem.dao.CustomerRepository;
import com.bank.BankSystem.model.Customer;
import com.bank.BankSystem.service.impl.AccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/smart")
public class ManagerController {


    @Autowired
    CustomerRepository customerRepository;


    @RequestMapping(value = "/approve", method=RequestMethod.GET)
    public ModelAndView approve(Model model)
    {
        ModelAndView mav = new ModelAndView();
        Iterable<Customer> customers = customerRepository.findAll();
        mav.addObject("customers", customers);
        mav.setViewName("/Approve");
        return mav;
    }



    @RequestMapping(value = "/approve/{id}/{firstName}",method= RequestMethod.GET)
    public String approve1(@PathVariable Long id,@PathVariable String firstName)
    {
        System.out.println("testtttttttttt "+id);
        System.out.println("testtttttttttt "+firstName);
        return  "/Approve";
    }


//    @RequestMapping(value = "/customer/{id}/{accountType}",method=RequestMethod.POST)
//    public String userDetail(@PathVariable("id") Long id,@PathVariable("accountType") String accountType, Model model){
////        System.out.println(+id);
////        System.out.println(accountType);
//        Optional<Customer> customer = customerRepository.findById(id);
//        model.addAttribute("customerdetail", customer);
//        return "CustomerDetail";
//    }
}
