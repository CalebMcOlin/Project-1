package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.LoanDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Account;
import com.revature.models.Loan;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountDAO accountDAO;
    private final UserDAO userDAO;
    private final LoanDAO loanDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO, UserDAO userDAO, LoanDAO loanDAO) {
        this.accountDAO = accountDAO;
        this.userDAO = userDAO;
        this.loanDAO = loanDAO;
    }

    public List<Account> getAllAccounts() {
        //are they the admin check?
        return accountDAO.findAll();
    }

    public Account getAccountByAccountId(int accountId) {
        //self user check or is admin = true
        if (accountId <= 0) {
            throw new IllegalArgumentException("Account with an id of 0 or less do not exist.");
        }
        Optional<Account> account = accountDAO.findById(accountId);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new IllegalArgumentException("Account with ID of " + accountId + " does not exist.");
        }
    }

    public List<Account> getAccountByUserId(int userId) {
        //self user check or is admin = true
        if (userId <= 0) {
            throw new IllegalArgumentException("User with an id of 0 or less do not exist.");
        }
        Optional<User> user = userDAO.findById(userId);
        List<Account> accounts;
        if (user.isPresent()) {
            accounts = accountDAO.findByUser(user);
        } else {
            throw new IllegalArgumentException("User with ID of " + userId + " do not exist.");
        }
        if (accounts.isEmpty()) {
            throw new IllegalArgumentException("User with ID of " + userId + " does not have any account.");
        } else {
            return accounts;
        }
    }

    public Account insertAccount(Account account, int userId) {
        //self user check. Low Prio
        if (userId <= 0) {
            throw new IllegalArgumentException("User with an id of 0 or less do not exist.");
        }
        if (account.getAccountBalance() <= 0) {
            throw new IllegalArgumentException("Account balance can not be negative number.");
        }
        Optional<User> user = userDAO.findById(userId);
        if (user.isPresent()) {
            account.setUser(user.get());
            return accountDAO.save(account);
        } else {
            throw new IllegalArgumentException("User could not be found. Aborting Insert...");
        }
    }

    public Account applyInterestRateByAccountId(int accountId) {
        //admin check
        Optional<Account> originalAccount = accountDAO.findById(accountId);
        if (originalAccount.isPresent()) {
            DecimalFormat df2 = new DecimalFormat("###.##");
            double interestRate = originalAccount.get().getAccountInterestRate();
            double oldBalance = originalAccount.get().getAccountBalance();
            double newBalance = Double.parseDouble(df2.format(oldBalance * (interestRate + 1)));

            Account updatedAccount = originalAccount.get();
            updatedAccount.setAccountBalance(newBalance);
            return accountDAO.save(updatedAccount);
        } else {
            throw new IllegalArgumentException("Account was not found! Aborting Interest Update.");
        }
    }

    public Optional<Account> deleteAccount(int accountId) {
        //admin check?
        if (accountId <= 0) {
            throw new IllegalArgumentException("Account with an id of 0 or less do not exist.");
        }
        Optional<Account> deletedAccount = accountDAO.findById(accountId);
        if (deletedAccount.isEmpty()) {
            throw new IllegalArgumentException("User with ID of " + accountId + " do not exist.");
        } else {
            List<Loan> loans = loanDAO.findByAccount(deletedAccount);
            for (Loan loan : loans) {
                loanDAO.delete(loan);
            }
            accountDAO.deleteById(accountId);
            return deletedAccount;
        }
    }
}