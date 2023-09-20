package com.revature.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
@Component
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int balance;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "account")
    private List<Loan> loans;

    public Account() {
    }

    public Account(int balance) {
        this.balance = balance;
    }

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getAccountId() {
        return id;
    }

    public void setAccountId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return balance;
    }

    public void setAmount(int balance) {
        this.balance = balance;
    }
}
