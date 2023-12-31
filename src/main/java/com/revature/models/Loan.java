package com.revature.models;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "loans")
@Component
public class Loan {

    @Id // Creating the PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;

    @Column(nullable = false, columnDefinition="DECIMAL(10,2) default '100.00'")
    private double loanAmount;

    @NonNull
    private boolean loanIsApproved;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST) // Creating the FK
    @JoinColumn(name = "accountId")
    private Account account;

    public Loan() {
    }

    public Loan(double loanAmount) {
        this.loanAmount = loanAmount;
        this.loanIsApproved = false;
    }

    public Loan(double loanAmount, boolean loanIsApproved) {
        this.loanAmount = loanAmount;
        this.loanIsApproved = loanIsApproved;
    }

    public Loan(double loanAmount, boolean loanIsApproved, Account account) {
        this.loanAmount = loanAmount;
        this.loanIsApproved = loanIsApproved;
        this.account = account;
    }

    public Loan(int loanId, double loanAmount, boolean loanIsApproved, Account account) {
        this.loanId = loanId;
        this.loanAmount = loanAmount;
        this.loanIsApproved = loanIsApproved;
        this.account = account;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public boolean isLoanIsApproved() {
        return loanIsApproved;
    }

    public void setLoanIsApproved(boolean loanIsApproved) {
        this.loanIsApproved = loanIsApproved;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", loanAmount=" + loanAmount +
                ", loanIsApproved=" + loanIsApproved +
                ", accountId=" + account +
                '}';
    }
}
