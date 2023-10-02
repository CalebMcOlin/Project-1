package com.revature.controllers;

//This controller will handle all things related to auth (login and register)
//insertUser should probably go in here, since you need to be able to create a user before logging in as them

import com.revature.models.AuthResponseDTO;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.utils.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController //makes a class a bean and allows for automatic Java <-> JSON conversions
@RequestMapping("/auth") //any request going to 5000/p1/auth will go here
@CrossOrigin() //configuring this will allow requests from specified origins
public class AuthController {


    private UserService userService;
    //autowire a JwtTokenUtil and AuthenticationManager
    private AuthenticationManager authManager;
    private JwtTokenUtil jwtUtil;

    private PasswordEncoder passwordEncoder;


    static public HttpSession ses;

    @Autowired
    public AuthController(AuthenticationManager authManager, JwtTokenUtil jwtUtil, UserService userService, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    //this method accepts user login credentials in the RequestBody and Validates those credentials
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDTO lDTO, HttpServletRequest request){
        try{

            //attempt to log in (notice no direct calls of the DAO)
            //this is where the username and password go to get validated
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(lDTO.getUsername(), lDTO.getPassword())
            );

            //build up the employee based on the validation
            User user = (User) authentication.getPrincipal();

            //if the user is valid, generate our JWT!
            String accessToken = jwtUtil.generateAccessToken(user);

            //generate our AuthResponseDTO and send it back with a 200 status code
            AuthResponseDTO aDTO = new AuthResponseDTO(user.getUsername(), accessToken);

            //TODO: Start HTTP session with session attributes
            User loggedInUser = userService.login(lDTO);
            System.out.println(loggedInUser);

            ses = request.getSession();
            System.out.println("session " + ses);
            ses.setAttribute("username", loggedInUser.getUsername());
            ses.setAttribute("userIsAdmin", loggedInUser.getUserIsAdmin());

            return ResponseEntity.ok().body(aDTO);

        } catch (BadCredentialsException e){
            //if login fails, return a 401 (UNAUTHORIZED) and the exception message
            System.out.println("BAD CREDENTIALS!!");
            return ResponseEntity.status(401).body(e.getMessage());
        }

    }

    @PostMapping("/new")
    public ResponseEntity<Object> insertUser(@RequestBody User user){

        try{
            //encode the incoming password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            String accessToken = jwtUtil.generateAccessToken(user);
            //send the user to the service. Saving it to a variable (for readability)
            userService.insertUser(user);
            AuthResponseDTO aDTO = new AuthResponseDTO(user.getUsername(), accessToken);
            //return a 202 (ACCEPTED) status code, as well as the new user in the response body

            return ResponseEntity.ok().body(aDTO);
            //accepted() is a shorthand of .status(202). They do the same thing

        } catch(IllegalArgumentException e){
            e.printStackTrace();
            //return a 400 status code (BAD REQUEST) and error message
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}