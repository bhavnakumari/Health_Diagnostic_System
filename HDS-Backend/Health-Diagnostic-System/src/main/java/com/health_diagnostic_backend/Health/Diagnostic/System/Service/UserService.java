package com.health_diagnostic_backend.Health.Diagnostic.System.Service;

import com.health_diagnostic_backend.Health.Diagnostic.System.Entity.User;
import com.health_diagnostic_backend.Health.Diagnostic.System.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Encrypts passwords securely
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register a new user by encrypting their password
    // create
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password
        return userRepository.save(user); // Save user in MongoDB
    }
    // Gets a user
    // Retrieve
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }



}
