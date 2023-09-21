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

    @NonNull
    private int loanAmount;

    private boolean loanIsApproved;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Creating the FK
    @JoinColumn(name = "accountId")
    private Account accountId;

    public Loan() {
    }

    public Loan(int loanAmount, boolean loanIsApproved, Account accountId) {
        this.loanAmount = loanAmount;
        this.loanIsApproved = loanIsApproved;
        this.accountId = accountId;
    }

    public Loan(int loanId, int loanAmount, boolean loanIsApproved, Account accountId) {
        this.loanId = loanId;
        this.loanAmount = loanAmount;
        this.loanIsApproved = loanIsApproved;
        this.accountId = accountId;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public boolean isLoanIsApproved() {
        return loanIsApproved;
    }

    public void setLoanIsApproved(boolean loanIsApproved) {
        this.loanIsApproved = loanIsApproved;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", loanAmount=" + loanAmount +
                ", loanIsApproved=" + loanIsApproved +
                ", accountId=" + accountId +
                '}';
    }
}
