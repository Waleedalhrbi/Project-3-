package org.example.bankmanagementsystemsecured.Repository;

import org.example.bankmanagementsystemsecured.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findCustomerById(Integer id);

    @Query("select cu from Customer cu where cu.userCustomer.username=?1")
    Customer findCustomerByUserCustomer(String username);
}
