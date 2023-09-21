package com.revature.models;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Component
public class User {

    @Id // Creating the PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, unique = true)
    private String userUsername;

    @Column(nullable = false)
    private String userPassword;

    @NonNull
    private boolean userIsAdmin;

    public User() {
    }

    public User(String userUsername, String userPassword, boolean userIsAdmin) {
        this.userUsername = userUsername;
        this.userPassword = userPassword;
        this.userIsAdmin = userIsAdmin;
    }

    public User(int userId, String userUsername, String userPassword, boolean userIsAdmin) {
        this.userId = userId;
        this.userUsername = userUsername;
        this.userPassword = userPassword;
        this.userIsAdmin = userIsAdmin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isUserIsAdmin() {
        return userIsAdmin;
    }

    public void setUserIsAdmin(boolean userIsAdmin) {
        this.userIsAdmin = userIsAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userUsername='" + userUsername + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userIsAdmin=" + userIsAdmin +
                '}';
    }
}