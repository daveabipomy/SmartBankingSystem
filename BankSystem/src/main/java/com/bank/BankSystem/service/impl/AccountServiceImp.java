package com.bank.BankSystem.service.impl;

import com.bank.BankSystem.dao.AccountRepostitory;
import com.bank.BankSystem.dao.AddressRepository;
import com.bank.BankSystem.dao.LoginRepository;
import com.bank.BankSystem.dao.RequestNewAccountRepository;
import com.bank.BankSystem.model.Address;
import com.bank.BankSystem.model.Customer;
import com.bank.BankSystem.model.Account;
import com.bank.BankSystem.model.Login;
import com.bank.BankSystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Service
public class AccountServiceImp implements AccountService {


    @Autowired
    private RequestNewAccountRepository requestNewAccountRepository;

    @Autowired
    private AccountServiceImp accountServiceImp;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AccountRepostitory accountRepostitory;

    @Autowired
    private UploadServiceImpl uploadService;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;




    private Session getSession(){
        Session session = null;

        try {
            session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        }catch (Exception e){
            e.printStackTrace();
        }

        return session;
    }
	public Account getAccount(String accountNumber){
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Account.class);
        Root contactRoot = criteria.from(Account.class);
        String queryVal = accountNumber + "%";
        criteria.select(contactRoot).where(builder.like(contactRoot.get("accountnumber"), queryVal));
        return (Account) session.createQuery(criteria).getSingleResult();
    }
		public Login register(Login login, String accountnumber) {
        Account getAccount=accountServiceImp.getAccount(accountnumber);
        System.out.println(getAccount.getAccountNumber());

        return loginRepository.save(login);
    }

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

    @Override
    public Account findAccount(String accountNumber) {
        return accountRepostitory.findByAccountNumber(accountNumber);
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



    public Account updateBalance(Account account)
    {
        Session session = null;

        try {
            session = getSession();
            session.beginTransaction();
            session.merge(account);
            session.getTransaction().commit();
            getSession().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;


    }
}
