package com.revature.doas;

import com.revature.models.Account;
import com.revature.models.Loan;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanDAO extends JpaRepository<Loan, Integer> {
    public List<Loan> findByAccount(Optional<Account> account);
}
