package com.revature.doas;

import com.revature.models.Account;
import com.revature.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDAO extends JpaRepository<Loan, Integer> {
}
