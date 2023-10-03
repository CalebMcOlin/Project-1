package com.revature.controllers;

import com.revature.models.Account;
import com.revature.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/accounts")
@CrossOrigin()
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok().body(accountService.getAllAccounts());
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Object> getAccountByAccountId(@PathVariable("accountId") int accountId) {
        try {
            return ResponseEntity.accepted().body(accountService.getAccountByAccountId(accountId));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getAccountByUserId(@PathVariable("userId") int userId) {
        try {
            return ResponseEntity.accepted().body(accountService.getAccountByUserId(userId));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/user/{userId}/new")
    public ResponseEntity<Object> insertAccount(@RequestBody Account account, @PathVariable("userId") int userId) {
        try {
            Account insertedAccount = accountService.insertAccount(account, userId);
            return ResponseEntity.accepted().body(insertedAccount);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{accountId}/balance")
    public ResponseEntity<Object> updateAccountBalanceByAccountId(@PathVariable("accountId") int accountId, @RequestBody double amount) {
        try {
            Account account = accountService.getAccountByAccountId(accountId);
            Account updatedAccount = accountService.updateAccountBalance(account, amount);
            return ResponseEntity.accepted().body(updatedAccount);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{accountId}/interest")
    public ResponseEntity<Object> applyInterestRateByAccountId(@PathVariable("accountId") int accountId) {
        try {
            return ResponseEntity.accepted().body(accountService.applyInterestRateByAccountId(accountId));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{accountId}/delete")
    public ResponseEntity<Object> deleteAccount(@PathVariable("accountId") int accountId) {
        try {
            return ResponseEntity.accepted().body(accountService.deleteAccount(accountId));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
