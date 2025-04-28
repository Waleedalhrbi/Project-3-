package org.example.bankmanagementsystemsecured.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bankmanagementsystemsecured.Api.ApiResponse;
import org.example.bankmanagementsystemsecured.Model.MyUser;
import org.example.bankmanagementsystemsecured.Service.AccountService;
import org.example.bankmanagementsystemsecured.Service.CustomerService;
import org.example.bankmanagementsystemsecured.Service.EmployeeService;
import org.example.bankmanagementsystemsecured.Service.MyUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserService myUserService;
    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final AccountService accountService;

    @GetMapping("/get-all-accounts")
    public ResponseEntity getAllAccount() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccounts());
    }
    @GetMapping("/get-all-users")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(myUserService.getAllUsers());
    }


    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody @Valid MyUser myUser){
        myUserService.addUser(myUser);
        return ResponseEntity.status(200).body(new ApiResponse("User Added"));
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid MyUser user){
        myUserService.updateUser(myUser.getUsername(),user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User updated"));
    }


    @DeleteMapping("/delete/{username}")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser myUser, @PathVariable String username){
        myUserService.deleteUser(username);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User deleted"));
    }
}
