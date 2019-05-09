package com.bank.BankSystem.service.impl;

import com.bank.BankSystem.dao.AddressRepository;
import com.bank.BankSystem.dao.RequestNewAccountRepository;
import com.bank.BankSystem.model.Address;
import com.bank.BankSystem.model.Customer;
import com.bank.BankSystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class AccountServiceImp implements AccountService {


    @Autowired
    private RequestNewAccountRepository requestNewAccountRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UploadServiceImpl uploadService;



    public Customer requestNewAccount(Customer customer, MultipartFile file)
    {
        String thumbnailName=uploadService.uploadImage(file);
        System.out.println("profile pic  "+thumbnailName);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/smart/downloadFile/")
                .path(thumbnailName)
                .toUriString();
        Address address=new Address();
        address.setStreet(customer.getAddress().getStreet());
        address.setCity(customer.getAddress().getCity());
        address.setState(customer.getAddress().getState());
        address.setZip(customer.getAddress().getZip());


        addressRepository.save(address);

        Customer cust=new Customer();
        cust.setFirstName(customer.getFirstName());
        cust.setLastName(customer.getLastName());
        cust.setSex(customer.getSex());
        cust.setSsn(customer.getSsn());
        cust.setPhoto(fileDownloadUri);


        cust.setDob(customer.getDob());
        cust.setEmail(customer.getEmail());
        cust.setPhone(customer.getPhone());
        cust.setAddress(address);

        System.out.println(customer.getDob());
        return  requestNewAccountRepository.save(cust);
    }



//    @Override
//    public int accountAuthentication(String username, String password) {
//        int count=0;
//        for(Account test:accountRepository.findAll())
//        {
//            if(test.name.equals(username) && test.password.equals(password))
//            {
//                count+=1;
//            }
//        }
//        return  count;
//    }
}
