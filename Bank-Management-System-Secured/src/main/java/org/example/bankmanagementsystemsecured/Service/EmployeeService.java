package org.example.bankmanagementsystemsecured.Service;

import lombok.RequiredArgsConstructor;
import org.example.bankmanagementsystemsecured.Api.ApiException;
import org.example.bankmanagementsystemsecured.DTO.EmployeeDTO;
import org.example.bankmanagementsystemsecured.Model.Employee;
import org.example.bankmanagementsystemsecured.Model.MyUser;
import org.example.bankmanagementsystemsecured.Repository.EmployeeRepository;
import org.example.bankmanagementsystemsecured.Repository.MyUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {



    private final EmployeeRepository employeeRepository;
    private final MyUserRepository myUserRepository;

    private final MyUserService myUserService;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void addEmployee(EmployeeDTO employeeDTO) {
        MyUser myUser = new MyUser(employeeDTO.getUsername(),employeeDTO.getPassword(), employeeDTO.getName(), "EMPLOYEE", employeeDTO.getEmail());


        myUserService.addUser(myUser);


        Employee employee = new Employee(null, employeeDTO.getPosition(), employeeDTO.getSalary(), myUserRepository.findMyUserByUsername(employeeDTO.getUsername()));
        employeeRepository.save(employee);
    }


    public void updateEmployee(String username, EmployeeDTO EmployeeDTO) {
        MyUser user = myUserRepository.findMyUserByUsername(username);
        if (user == null) {
            throw new ApiException("Employee not found");
        }
        MyUser myUser = new MyUser(EmployeeDTO.getUsername(), EmployeeDTO.getPassword(), EmployeeDTO.getName(), "EMPLOYEE", EmployeeDTO.getEmail());
        Employee employee = employeeRepository.findEmployeesByUsername(username);
        employee.setPosition(EmployeeDTO.getPosition());
        employee.setSalary(EmployeeDTO.getSalary());
        myUserService.updateUser(username, myUser);
        employeeRepository.save(employee);
    }

    public void deleteEmployee(String username) {
        MyUser user = myUserRepository.findMyUserByUsername(username);
        Employee employee = employeeRepository.findEmployeesByUsername(username);

        if (user == null) {
            throw new ApiException("Employee Not found");
        }
        myUserRepository.delete(user);
        employeeRepository.delete(employee);


    }
}
