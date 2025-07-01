package com.example.cricScore.controller;

import com.example.cricScore.model.User;
import com.example.cricScore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    // Register new user
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        try {
            userService.createUser(user.getUsername(), user.getPassword());
            return "User registered successfully!";
        } catch (RuntimeException e) {
            return e.getMessage();  // This will return the error message like "Username already exists"
        }
    }

    // Login existing user
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean authenticated = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticated) {
            return ResponseEntity.ok("Login successful!");
        } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password!");
    }
    }
    // Get user by username
    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return user;  // Return the user if found
        } else {
            throw new RuntimeException("User not found");  // You can customize the error message
        }
    }
}
