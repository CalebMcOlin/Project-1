package com.revature.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, unique = true)
    private String userUsername;

    @Column(nullable = false)
    private String userPassword;

    public User() {
    }

    public User(String userUsername, String userPassword) {
        this.userUsername = userUsername;
        this.userPassword = userPassword;
    }

    public User(int id, String userUsername, String userPassword) {
        this.userId = id;
        this.userUsername = userUsername;
        this.userPassword = userPassword;
    }

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public String getUsername() {
        return userUsername;
    }

    public void setUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getPassword() {
        return userPassword;
    }

    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
