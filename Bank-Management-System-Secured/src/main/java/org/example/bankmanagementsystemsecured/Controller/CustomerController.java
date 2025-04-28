package org.example.bankmanagementsystemsecured.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bankmanagementsystemsecured.Api.ApiResponse;
import org.example.bankmanagementsystemsecured.DTO.CustomerDTO;
import org.example.bankmanagementsystemsecured.Model.MyUser;
import org.example.bankmanagementsystemsecured.Service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("/get-all-customers")
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
    }

    @PostMapping("/add-customer")
    public ResponseEntity addCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        customerService.addCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer added"));
    }


    @PutMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid CustomerDTO customerDTO){
        customerService.updateCustomer(myUser.getUsername(), customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer updated"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal MyUser myUser){
        customerService.deleteCustomer(myUser.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer deleted"));
    }
}
