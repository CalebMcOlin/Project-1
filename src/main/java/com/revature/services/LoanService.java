package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.LoanDAO;
import com.revature.models.Account;
import com.revature.models.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanDAO loanDAO;
    private final AccountDAO accountDAO;

    @Autowired
    public LoanService(LoanDAO loanDAO, AccountDAO accountDAO) {
        this.loanDAO = loanDAO;
        this.accountDAO = accountDAO;
    }

    public List<Loan> getAllLoans() {
        return loanDAO.findAll();
    }

    public Loan getLoanById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Loans with an id of 0 or less do not exist.");
        }

        Optional<Loan> loan = loanDAO.findById(id);
        if (loan.isPresent()) {
            return loan.get();
        } else {
            throw new IllegalArgumentException("Loan with ID of " + id + " does not exist.");
        }
    }

    public Loan updateLoanIsApprovedById(int id, boolean isApproved) {
        if (id <= 0) {
            throw new IllegalArgumentException("Loans with an id of 0 or less do not exist.");
        }

        Optional<Loan> oldLoan = loanDAO.findById(id);
        if (oldLoan.isPresent()) {
            Loan newLoan = oldLoan.get();
            newLoan.setLoanIsApproved(isApproved); //change the username to what was given in the params
            return loanDAO.save(newLoan); //perform the update
        } else {
            throw new IllegalArgumentException("Loan with ID of " + id + " does not exist.");
        }
    }

    public Loan insertLoan(Loan loan, int accountId) {
        if (accountId <= 0) {
            throw new IllegalArgumentException("Accounts with an id of 0 or less do not exist.");
        }

        if (loan.getLoanAmount() <= 0) {
            throw new IllegalArgumentException("Loan Request can not be $0.00 or less.");
        }

        Optional<Account> account = accountDAO.findById(accountId);
        if (account.isPresent()) {
            loan.setAccount(account.get());
            return loanDAO.save(loan);
        } else {
            throw new IllegalArgumentException("Account could not be found. Aborting Insert...");
        }
    }

}
