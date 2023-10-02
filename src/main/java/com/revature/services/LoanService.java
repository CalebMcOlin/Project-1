package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.LoanDAO;
import com.revature.models.Account;
import com.revature.models.Loan;
import com.revature.controllers.AuthController;
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
        //admin check
        boolean adminChk = (boolean) AuthController.ses.getAttribute("userIsAdmin");
        if (!adminChk) {
            throw new IllegalArgumentException("You do not have permission to access this information");
        }
        return loanDAO.findAll();

    }

    public Loan getLoanById(int id) {
        //self check or admin = true
        boolean adminChk = (boolean) AuthController.ses.getAttribute("userIsAdmin");
        int sesId = (int) AuthController.ses.getAttribute("userId");

        Optional<Loan> loan = loanDAO.findById(id);
        if (loan.isEmpty()) {
            throw new IllegalArgumentException("Loans with an id of " + id + " do not exist.");
        }

        Loan receivedLoan = loan.get();
        Account account = receivedLoan.getAccount();
        int userId= account.getUser().getUserId();

        if (!adminChk && sesId != userId) {
            throw new IllegalArgumentException("You do not have permission to access this information");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("Loans with an id of 0 or less do not exist.");
        }
        return receivedLoan;
    }

    public Loan updateLoanIsApprovedById(int id, boolean isApproved) {
        //admin check
        boolean adminChk = (boolean) AuthController.ses.getAttribute("userIsAdmin");

        if (!adminChk) {
            throw new IllegalArgumentException("You do not have permission to access this information");
        }
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
        Optional<Account> account = accountDAO.findById(accountId);
        if (account.isEmpty()){
            throw new IllegalArgumentException("Account could not be found. Aborting Insert...");
        }
        Account gotAccount = account.get();
        int sesId = (int) AuthController.ses.getAttribute("userId");
        int userId = gotAccount.getUser().getUserId();
        boolean adminChk = (boolean) AuthController.ses.getAttribute("userIsAdmin");
        if (!adminChk && sesId != userId) {
            throw new IllegalArgumentException("You do not have permission to access this information");
        }
        if (accountId <= 0) {
            throw new IllegalArgumentException("Accounts with an id of 0 or less do not exist.");
        }

        if (loan.getLoanAmount() <= 0) {
            throw new IllegalArgumentException("Loan Request can not be $0.00 or less.");
        }
        loan.setAccount(gotAccount);
        return loanDAO.save(loan);
    }

}
