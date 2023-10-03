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

    @GetMapping("/{loanId}")
    public ResponseEntity<Object> getLoanById(@PathVariable("loanId") int id) {
        try {
            return ResponseEntity.ok().body(loanService.getLoanById(id));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{loanId}/approval")
    public ResponseEntity<Object> updateLoanIsApprovedById(@PathVariable("loanId") int id, @RequestBody boolean isApproved) {
        try {
            return ResponseEntity.accepted().body(loanService.updateLoanIsApprovedById(id, isApproved));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/account/{accountId}/new")
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
