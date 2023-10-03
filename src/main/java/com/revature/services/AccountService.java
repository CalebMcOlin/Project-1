package com.revature.services;

import com.revature.controllers.AuthController;
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
        boolean adminChk = (boolean) AuthController.ses.getAttribute("userIsAdmin");
        if (!adminChk) {
            throw new IllegalArgumentException("You do not have permission to access this information");
        }
        return accountDAO.findAll();
    }

    public Account getAccountByAccountId(int accountId) {
        //self user check or is admin
        if (accountId <= 0) {
            throw new IllegalArgumentException("Account with an id of 0 or less cannot exist.");
        }

        Optional<Account> account = accountDAO.findById(accountId);
        if (account.isEmpty()) {
            throw new IllegalArgumentException("Account with ID of " + accountId + " does not exist.");
        }

        Account foundAccount= account.get();
        boolean adminChk = (boolean) AuthController.ses.getAttribute("userIsAdmin");
        int userId= foundAccount.getUser().getUserId();
        int sesId = (int) AuthController.ses.getAttribute("userId");
        if (!adminChk && sesId != userId) {
            throw new IllegalArgumentException("You do not have permission to access this information");
        }

        return foundAccount;
    }

    public List<Account> getAccountByUserId(int userId) {
        //self user check or is admin DONE
        boolean adminChk = (boolean) AuthController.ses.getAttribute("userIsAdmin");
        int sesId = (int) AuthController.ses.getAttribute("userId");
        if (!adminChk && sesId != userId) {
            throw new IllegalArgumentException("You do not have permission to access this information");
        }
        if (userId <= 0) {
            throw new IllegalArgumentException("User with an id of 0 or less cannot exist.");
        }
        Optional<User> user = userDAO.findById(userId);
        List<Account> accounts;
        if (user.isPresent()) {
            accounts = accountDAO.findByUser(user);
        } else {
            throw new IllegalArgumentException("User with ID of " + userId + " does not exist.");
        }
        if (accounts.isEmpty()) {
            throw new IllegalArgumentException("User with ID of " + userId + " does not have any account.");
        }
        return accounts;
    }

    public Account insertAccount(Account account, int userId) {
        boolean adminChk = (boolean) AuthController.ses.getAttribute("userIsAdmin");
        int sesId = (int) AuthController.ses.getAttribute("userId");
        if (!adminChk && sesId != userId) {
            throw new IllegalArgumentException("You do not have permission to access this information");
        }
        if (userId <= 0) {
            throw new IllegalArgumentException("User with an id of 0 or less cannot exist.");
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

    public Account updateAccountBalance(Account account, double amount) {
        if (amount == 0) {
            throw new IllegalArgumentException("You cannot withdraw or deposit $0, please provide an amount");
        }

        String amountString = Double.toString(amount);
        int decimalPlaces = amountString.length() - amountString.indexOf(".") - 1;
        if (decimalPlaces > 2) {
            throw new IllegalArgumentException("You cannot withdraw or deposit any amount less than 1 cent");
        }
        
        double newAmount = account.getAccountBalance() + amount;
        if (newAmount < 0) {
            throw new IllegalArgumentException("Withdraw amount is greater than this account's remaining balance");
        }
        account.setAccountBalance(newAmount);
        return accountDAO.save(account);
    }
    
    public Account applyInterestRateByAccountId(int accountId) {
        boolean adminChk = (boolean) AuthController.ses.getAttribute("userIsAdmin");
        if (!adminChk) {
            throw new IllegalArgumentException("You do not have permission to access this information");
        }
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
            throw new IllegalArgumentException("Account was not found! Aborting Interest Update...");
        }
    }

    public Optional<Account> deleteAccount(int accountId) {
        boolean adminChk = (boolean) AuthController.ses.getAttribute("userIsAdmin");
        if (!adminChk) {
            throw new IllegalArgumentException("You do not have permission to access this information");
        }
        if (accountId <= 0) {
            throw new IllegalArgumentException("Account with an id of 0 or less cannot exist.");
        }
        Optional<Account> deletedAccount = accountDAO.findById(accountId);
        if (deletedAccount.isEmpty()) {
            throw new IllegalArgumentException("User with ID of " + accountId + " does not exist.");
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
