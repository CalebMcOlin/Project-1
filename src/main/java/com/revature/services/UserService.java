package com.revature.services;

import com.revature.doas.UserDAO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User getUserById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("User with an id of 0 or less do not exist.");
        }
        Optional<User> user = userDAO.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new IllegalArgumentException("User with ID of " + id + " does not exist.");
        }
    }

    public User insertUser(User user) throws IllegalArgumentException {
        if (user.getUserUsername().equals(null) || user.getUserUsername().isEmpty()) {
            throw new IllegalArgumentException("Username must not be empty!");
        }
        if (user.getUserPassword().equals(null) || user.getUserPassword().isEmpty()) {
            throw new IllegalArgumentException("Password must not be empty!");
        }
        if (userDAO.findByUserUsername(user.getUserUsername()).isEmpty()) {
            return userDAO.save(user);
        } else {
            throw new IllegalArgumentException("Username already exist!");
        }
    }
}
