package org.example.bankmanagementsystemsecured.Repository;

import org.example.bankmanagementsystemsecured.Model.Account;
import org.example.bankmanagementsystemsecured.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    Account findAccountById(Integer id);

    List<Account> findAllByCustomer(Customer customer);
}
