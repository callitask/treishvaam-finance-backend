package com.treishvaam.finance.repository;

import com.treishvaam.finance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // This custom method is crucial for the security setup to find a user by their name
    Optional<User> findByUsername(String username);
}
