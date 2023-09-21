package com.revature.controllers;

import com.revature.models.Loan;
import com.revature.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/loans")
@CrossOrigin()
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok().body(loanService.getAllLoans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLoanById(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok().body(loanService.getLoanById(id));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateLoanIsApprovedById(@PathVariable("id") int id, @RequestBody boolean isApproved) {
        try {
            return ResponseEntity.accepted().body(loanService.updateLoanIsApprovedById(id, isApproved));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/new/{accountId}")
    public ResponseEntity<Object> insertLoan(@RequestBody Loan loan, @PathVariable("accountId") int accountId) {
        try {
            Loan insertedLoan = loanService.insertLoan(loan, accountId);
            return ResponseEntity.accepted().body(insertedLoan);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
