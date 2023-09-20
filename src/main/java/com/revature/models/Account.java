package com.revature.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Component
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Column(nullable = false)
    private int accountAmount;

    @OneToMany()
    private int userId;

    public Account() {
    }

    public Account(int accountAmount) {
        this.accountAmount = accountAmount;
    }

    public Account(int accountId, int accountAmount) {
        this.accountId = accountId;
        this.accountAmount = accountAmount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAmount() {
        return accountAmount;
    }

    public void setAmount(int accountAmount) {
        this.accountAmount = accountAmount;
    }
}
