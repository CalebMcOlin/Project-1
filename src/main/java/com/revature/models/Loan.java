package com.revature.models;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "loans")
@Component
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private int amount;

    private boolean isApproved;

    @ManyToOne
    private Account account;

    public Loan() {
    }

    public Loan(int amount, boolean isApproved) {
        this.amount = amount;
        this.isApproved = isApproved;
    }

    public Loan(int id, int amount, boolean isApproved) {
        this.id = id;
        this.amount = amount;
        this.isApproved = isApproved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
