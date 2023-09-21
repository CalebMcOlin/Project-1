package com.revature.services;

import com.revature.doas.LoanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private final LoanDAO loanDAO;

    @Autowired
    public LoanService(LoanDAO loanDAO) {
        this.loanDAO = loanDAO;
    }
}
