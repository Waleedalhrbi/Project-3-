package org.example.bankmanagementsystemsecured.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bankmanagementsystemsecured.Api.ApiResponse;
import org.example.bankmanagementsystemsecured.DTO.EmployeeDTO;
import org.example.bankmanagementsystemsecured.Model.MyUser;
import org.example.bankmanagementsystemsecured.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/get-all-employees")
    public ResponseEntity getAllEmployees() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
    }

    @PostMapping("/add-employee")
    public ResponseEntity addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        employeeService.addEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee added"));
    }


    @PutMapping("/update-employee")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.updateEmployee(myUser.getUsername(), employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee updated"));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal MyUser myUser,@PathVariable String username){
        employeeService.deleteEmployee(username);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee deleted"));
    }
}
