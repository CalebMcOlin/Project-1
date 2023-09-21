package com.revature.services;

import com.revature.doas.AccountDAO;
import com.revature.doas.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountDAO accountDAO;
    private final UserDAO userDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO,UserDAO userDAO) {
        this.accountDAO = accountDAO;
        this.userDAO = userDAO;
    }
}
