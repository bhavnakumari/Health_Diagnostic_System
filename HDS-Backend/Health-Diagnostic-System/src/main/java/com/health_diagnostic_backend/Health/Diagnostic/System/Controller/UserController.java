package com.health_diagnostic_backend.Health.Diagnostic.System.Controller;

import com.health_diagnostic_backend.Health.Diagnostic.System.Entity.User;
import com.health_diagnostic_backend.Health.Diagnostic.System.Security.JwtUtil;
import com.health_diagnostic_backend.Health.Diagnostic.System.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    PasswordEncoder passwordEncoder;

    //EndPoint for user registration
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        if(userService.findByUsername(user.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("User already exists, Try Login");
        }
        else {
            userService.registerUser(user);
            return ResponseEntity.ok("Registration Done");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String,String> loginRequest){
        Optional<User> user= userService.findByUsername(loginRequest.get("username"));
        boolean passwordMatch= passwordEncoder.matches(loginRequest.get("password"),user.get().getPassword());

        // Check if the user exists and password matches
        if(user.isPresent() && passwordMatch){
            //Generate JWT Token
            String token = jwtUtil.generateToken(user.get().getUsername());
            //Return Token to client
            return ResponseEntity.ok(Map.of("token", token)); // Return token to client
        } else if (!user.isPresent() || !passwordMatch) {
            return ResponseEntity.status(401).body("Invalid username/password");
        }
        return ResponseEntity.badRequest().body("User is not registered");
    }
}
