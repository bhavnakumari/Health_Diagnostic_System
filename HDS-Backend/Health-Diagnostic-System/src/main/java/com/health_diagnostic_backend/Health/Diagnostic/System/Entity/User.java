package com.health_diagnostic_backend.Health.Diagnostic.System.Entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;

// Marks this as a MongoDB document stored in the "users" collection
@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id  // Primary key for MongoDB
    private String id;

    private String username; // Unique username for login

    private String password; // Hashed password stored securely

    private Set<String> roles; // Set of roles (e.g., USER, ADMIN)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}