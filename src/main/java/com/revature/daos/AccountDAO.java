package com.revature.daos;

import com.revature.models.Account;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer> {
    public List<Account> findByUser(Optional<User> user);
}
