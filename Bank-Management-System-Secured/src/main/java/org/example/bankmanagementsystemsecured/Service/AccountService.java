package org.example.bankmanagementsystemsecured.Service;

import lombok.RequiredArgsConstructor;
import org.example.bankmanagementsystemsecured.Api.ApiException;
import org.example.bankmanagementsystemsecured.Model.Account;
import org.example.bankmanagementsystemsecured.Model.Customer;
import org.example.bankmanagementsystemsecured.Repository.AccountRepository;
import org.example.bankmanagementsystemsecured.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> getMyAccount(Integer customer_Id) {
        Customer customer = customerRepository.findCustomerById(customer_Id);
        return accountRepository.findAllByCustomer(customer);
    }

    public void addAccount(Integer customer_Id, Account account) {
        Customer customer = customerRepository.findCustomerById(customer_Id);
        account.setCustomer(customer);
        accountRepository.save(account);

    }

    public void updateAccount(Integer customer_Id, Integer account_Id, Account account) {
        Account oldAccount = accountRepository.findAccountById(account_Id);

        if (oldAccount == null){
            throw new ApiException("Account not found");
        }


        if (oldAccount.getCustomer().getId() != customer_Id) {
            throw new ApiException("You can not update");
        }

        oldAccount.setAccountNumber(account.getAccountNumber());
        oldAccount.setBalance(account.getBalance());
        oldAccount.setIsActive(account.getIsActive());
        accountRepository.save(oldAccount);
    }



    public void deleteAccount(Integer customer_Id, Integer account_Id){
        Account oldAccount = accountRepository.findAccountById(account_Id);
        if (oldAccount == null){
            throw new ApiException("Account not found");
        }
        if (oldAccount.getCustomer().getId() != customer_Id) {
            throw new ApiException("You can not delete");
        }
        accountRepository.delete(oldAccount);
    }

    public Account viewAccountDetails(Integer customer_Id, Integer account_Id)
    {
        Account oldAccount = accountRepository.findAccountById(account_Id);
        if (oldAccount == null){
            throw new ApiException("Account not found");
        }
        if (oldAccount.getCustomer().getId() != customer_Id) {
            throw new ApiException("You can not see details");
        }

        return accountRepository.findAccountById(account_Id);
    }

    public void activeBankAccount(Integer customer_Id, Integer account_Id){

        Account oldAccount = accountRepository.findAccountById(account_Id);
        if (oldAccount == null){
            throw new ApiException("Account not found");
        }
        if (oldAccount.getCustomer().getId() != customer_Id) {
            throw new ApiException("You can not see details");
        }

        if (oldAccount.getIsActive()){
            throw new ApiException("Account Already Active");
        }


        oldAccount.setIsActive(true);
        accountRepository.save(oldAccount);
    }



    public void deposit(Integer customer_Id, Integer account_Id,Double amount){
        Account oldAccount = accountRepository.findAccountById(account_Id);

        if (oldAccount == null){
            throw new ApiException("Account not found");
        }

        if (oldAccount.getCustomer().getId() != customer_Id) {
            throw new ApiException("You can not see details");
        }

        if (oldAccount.getIsActive() == false){
            throw new ApiException("Account not active");
        }

        oldAccount.setBalance(oldAccount.getBalance() + amount);
        accountRepository.save(oldAccount);
    }



    public void withDraw(Integer customer_Id, Integer account_Id,Double amount){
        Account oldAccount = accountRepository.findAccountById(account_Id);

        if (oldAccount == null){
            throw new ApiException("Account not found");
        }

        if (oldAccount.getCustomer().getId() != customer_Id) {
            throw new ApiException("You can not see details");
        }

        if (oldAccount.getIsActive() == false){
            throw new ApiException("Account not active");
        }

        if (oldAccount.getBalance() < amount ){
            throw new ApiException("Balance not enough");
        }

        oldAccount.setBalance(oldAccount.getBalance() - amount);
        accountRepository.save(oldAccount);

    }

    public void transferFundsBetweenAccounts(Integer customer_Id, Integer yourAccount_Id, Integer targetAccount_Id, Double amount) {

        Account yourAccount = accountRepository.findAccountById(yourAccount_Id);
        Account targetAccount = accountRepository.findAccountById(targetAccount_Id);

        if (yourAccount == null) {
            throw new ApiException("Your account not found");
        }

        if (targetAccount == null) {
            throw new ApiException("Target account not found");
        }

        if (!yourAccount.getCustomer().getId().equals(customer_Id)) {
            throw new ApiException("You can not transfer from an account that is not yours");
        }

        if (!yourAccount.getIsActive()) {
            throw new ApiException("Your account is not active");
        }

        if (!targetAccount.getIsActive()) {
            throw new ApiException("Target account is not active");
        }

        if (yourAccount.getBalance() < amount) {
            throw new ApiException("Insufficient balance");
        }

        yourAccount.setBalance(yourAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);

        accountRepository.save(yourAccount);
        accountRepository.save(targetAccount);
    }


    public void blockAccount(Integer account_Id) {
        Account oldAccount = accountRepository.findAccountById(account_Id);

        if (oldAccount == null) {
            throw new ApiException("Account not found");
        }

        if (!oldAccount.getIsActive()) {
            throw new ApiException("Account is already blocked !!");
        }

        oldAccount.setIsActive(false);
        accountRepository.save(oldAccount);
    }



}
