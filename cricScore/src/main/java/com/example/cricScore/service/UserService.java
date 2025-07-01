package com.example.cricScore.service;

import com.example.cricScore.model.User;
import com.example.cricScore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user (register)
    public User createUser(String username, String password) {
        // Check if username already exists
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists");
        }

        // Save user directly without password encryption
        User user = new User(username, password);
        return userRepository.save(user);
    }

    // Login user (simple match)
    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }

        // Match username and password directly
        return user.getPassword().equals(password);
    }
    public User getUserByUsername(String username) {
    return userRepository.findByUsername(username);
    }
}
