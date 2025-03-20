package com.health_diagnostic_backend.Health.Diagnostic.System.Repository;

import com.health_diagnostic_backend.Health.Diagnostic.System.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByUsername(String username);
}
