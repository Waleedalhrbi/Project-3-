package org.example.bankmanagementsystemsecured.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bankmanagementsystemsecured.Api.ApiResponse;
import org.example.bankmanagementsystemsecured.Model.Account;
import org.example.bankmanagementsystemsecured.Model.MyUser;
import org.example.bankmanagementsystemsecured.Service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @GetMapping("/get-all-accounts")
    public ResponseEntity getAllAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccounts());
    }



    @GetMapping("/get-my-accounts")
    public ResponseEntity getMyAccounts(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getMyAccount(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addAccount(@AuthenticationPrincipal MyUser user, @RequestBody @Valid Account account){
        accountService.addAccount(user.getId(),account);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account added"));
    }


    @PutMapping("/update/{account_Id}")
    public ResponseEntity updateAccount(@AuthenticationPrincipal MyUser user, @PathVariable Integer account_Id,@RequestBody @Valid Account account){
        accountService.updateAccount(user.getId(),account_Id,account);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account updated"));
    }


    @DeleteMapping("/delete/{account_Id}")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal MyUser user,@PathVariable Integer account_Id){
        accountService.deleteAccount(user.getId(),account_Id);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account deleted"));

    }


    @GetMapping("view-account-details/{account_Id}")
    public ResponseEntity ViewAccountDetails(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_Id){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.viewAccountDetails(myUser.getId(),account_Id));
    }


    @PostMapping("active-bank-account/{account_Id}")
    public ResponseEntity ActiveBankAccount(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_Id){
        accountService.activeBankAccount(myUser.getId(),account_Id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account activated"));
    }


    @PostMapping("deposit-account/{account_Id}/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_Id,@PathVariable Double amount){
        accountService.deposit(myUser.getId(),account_Id,amount);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deposited done"));
    }


    @PostMapping("withdraw-account/{account_Id}/{amount}")
    public ResponseEntity withDraw(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_Id,@PathVariable Double amount){
        accountService.withDraw(myUser.getId(),account_Id,amount);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Withdraw done"));
    }

    @PostMapping("/transfer-funds-between-accounts/{yourAccount_Id}/{targetAccount_Id}/{amount}")
    public ResponseEntity transferFundsBetweenAccounts(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer yourAccount_Id,@PathVariable Integer targetAccount_Id,@PathVariable Double amount){
        accountService.transferFundsBetweenAccounts (myUser.getId(),yourAccount_Id,targetAccount_Id,amount);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Amount transferred done"));

    }

    @PostMapping("/block-account/{account_Id}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_Id){
        accountService.blockAccount(account_Id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account Blocked"));
    }
}
