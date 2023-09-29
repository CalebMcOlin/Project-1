package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers(){
        //admin check
        return userDAO.findAll();
    }

    public User insertUser(User user){
            return userDAO.save(user); //save the Employee to the DB, and return the saved Employee
    }

    public User findByUserId(int id){
        //self or admin = true
        if(id <= 0){
            throw new IllegalArgumentException("Users with an id of 0 or less surely can't exist!");
        }

        /*findById() from JpaRepository returns an "Optional"
        Optionals lend to code flexibility because it MAY or MAY NOT contain the requested object
        This helps us avoid NullPointerExceptions */
        Optional<User> user = userDAO.findById(id);

        //we can check if the optional has our value with .isPresent() or .isEmpty()
        if(user.isPresent()){
            return user.get(); //we can extract the Optional's data with .get()
        } else {
            throw new IllegalArgumentException("User id " + id + " does not exist!");
        }

    }


}
