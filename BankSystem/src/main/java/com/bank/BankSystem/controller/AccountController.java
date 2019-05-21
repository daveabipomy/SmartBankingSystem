package com.bank.BankSystem.controller;


import com.bank.BankSystem.model.Customer;
import com.bank.BankSystem.model.Login;
import com.bank.BankSystem.service.impl.AccountServiceImp;
//import jdk.vm.ci.meta.Value;
import com.bank.BankSystem.service.impl.UploadServiceImpl;
//import jdk.internal.loader.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.io.Resource;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Controller
@RequestMapping("/smart")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountServiceImp accountService;



    @Autowired
    private UploadServiceImpl uploadService;


    @RequestMapping("/customer")
    public String requestForm(Customer customer){
    return "NewCustomerRequestForm";
    }

	@RequestMapping("/register")
    public String register(Customer customer){
        return "Register";
    }



    @RequestMapping(value = {"/register"},method=RequestMethod.POST)
    public  String register(@RequestParam String email, @RequestParam String password,@RequestParam String accountnumber)
    {
        Login login=new Login();
        login.setUserName(email);
        login.setPassword(password);
        login.setRole("Customer");
        accountService.register(login,accountnumber);
        return "Register";

    }

    @RequestMapping(value={"/customer"},headers=("content-type=multipart/*"),method = RequestMethod.POST)
    public String requestForm(@Valid Customer customer, BindingResult result, Model model,@RequestParam("file") MultipartFile file)
    {
        System.out.println(customer.getDob());
        System.out.println(customer.getFirstName());
        Customer customeInsert=new Customer();
        customeInsert.setFirstName(customer.getFirstName());

        accountService.requestNewAccount(customer,file);
        return "NewCustomerRequestForm";
    }


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String root() {
        return "index";
    }


//    @RequestMapping(value="/accountList")
//    public String accountList(Model model) {
//        model.addAttribute("accountList", accountRepository.findAll());
//        return "accountList";
//    }
//    @RequestMapping(value = {"/login"},method=RequestMethod.POST)
//    public String login(@RequestParam String username, @RequestParam String password)
//    {
//
//        int count= accountService.accountAuthentication(username,password);
//
//        if(count>0)
//        {
//            return "home";
//        }
//        else
//        {
//            return "login";
//        }
//
//    }




    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = uploadService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }



}
