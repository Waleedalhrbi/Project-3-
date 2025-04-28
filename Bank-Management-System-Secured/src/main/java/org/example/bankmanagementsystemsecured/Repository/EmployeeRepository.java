package org.example.bankmanagementsystemsecured.Repository;

import org.example.bankmanagementsystemsecured.Model.Employee;
import org.example.bankmanagementsystemsecured.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    Employee findEmployeeById(Integer id);


    Employee findEmployeesByUserEmployee(MyUser myUser);


    @Query("select em from Employee em where em.userEmployee.username=?1")
    Employee findEmployeesByUsername(String username);

}
