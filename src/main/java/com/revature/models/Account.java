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

    @Column(nullable = false)
    private int accountBalance;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Creating the FK
    @JoinColumn(name = "userId")
    private User userId;

    public Account() {
    }

    public Account(int accountBalance, User userId) {
        this.accountBalance = accountBalance;
        this.userId = userId;
    }

    public Account(int accountId, int accountBalance, User userId) {
        this.accountId = accountId;
        this.accountBalance = accountBalance;
        this.userId = userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountBalance=" + accountBalance +
                ", userId=" + userId +
                '}';
    }
}
