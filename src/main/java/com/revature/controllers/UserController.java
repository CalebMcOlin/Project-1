package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin()
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllAccounts() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("id/{userId}")
    public ResponseEntity<Object> getUserByUserId(@PathVariable("userId") int userId){
        return null;
    }
}
