package org.example.bankmanagementsystemsecured.Service;

import lombok.RequiredArgsConstructor;
import org.example.bankmanagementsystemsecured.Api.ApiException;
import org.example.bankmanagementsystemsecured.DTO.CustomerDTO;
import org.example.bankmanagementsystemsecured.Model.Customer;
import org.example.bankmanagementsystemsecured.Model.MyUser;
import org.example.bankmanagementsystemsecured.Repository.CustomerRepository;
import org.example.bankmanagementsystemsecured.Repository.MyUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final MyUserRepository myUserRepository;

    private final MyUserService myUserService;

    public List<Customer> getAllCustomers(){
         return customerRepository.findAll();
    }


    public void addCustomer( CustomerDTO customerDTO){
        MyUser myUser=new MyUser(customerDTO.getUsername(),customerDTO.getPassword(),customerDTO.getName(),"CUSTOMER",customerDTO.getEmail());

        myUserService.addUser(myUser);

        Customer customer= new Customer(null, customerDTO.getPhoneNumber(), myUserRepository.findMyUserByUsername(customerDTO.getUsername()),null);
        customerRepository.save(customer);
    }



    public void updateCustomer(String username, CustomerDTO customerDTO) {
        MyUser oldUser = myUserRepository.findMyUserByUsername(username);
        if (oldUser == null) {
            throw new ApiException("Customer Not found");
        }
        MyUser myUser = new MyUser(customerDTO.getUsername(), customerDTO.getPassword(), customerDTO.getName(), "CUSTOMER", customerDTO.getEmail());
        Customer oldCustomer = customerRepository.findCustomerByUserCustomer(username);
        oldCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        myUserService.updateUser(username, myUser);
        customerRepository.save(oldCustomer);
    }
    public void deleteCustomer(String username){
        MyUser oldUser = myUserRepository.findMyUserByUsername(username);
        Customer oldCustomer=customerRepository.findCustomerByUserCustomer(username);

        if (oldUser == null) {
            throw new ApiException("Customer Not found");
        }
        myUserRepository.delete(oldUser);
        customerRepository.delete(oldCustomer);
    }
}
