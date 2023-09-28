package com.revature.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Component
public class Account {

    @Id // Creating the PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Column(nullable = false, columnDefinition="Decimal(10,2) default '100.00'")
    private double accountBalance;

    @Column(nullable = false, columnDefinition="Decimal(10,3) default '0.000'")
    private double accountInterestRate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Creating the FK
    @JoinColumn(name = "userId")
    private User user;

    public Account() {
    }

    public Account(double accountBalance, User userId) {
        this.accountBalance = accountBalance;
        this.accountInterestRate = 0.45;
        this.user = userId;
    }

    public Account(double accountBalance, double accountInterestRate, User userId) {
        this.accountBalance = accountBalance;
        this.accountInterestRate = accountInterestRate;
        this.user = userId;
    }

    public Account(int accountId, double accountBalance, double accountInterestRate, User userId) {
        this.accountId = accountId;
        this.accountBalance = accountBalance;
        this.accountInterestRate = accountInterestRate;
        this.user = userId;
    }

    public double getAccountInterestRate() {
        return accountInterestRate;
    }

    public void setAccountInterestRate(double accountInterestRate) {
        this.accountInterestRate = accountInterestRate;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountBalance=" + accountBalance +
                ", userId=" + user +
                '}';
    }
}
