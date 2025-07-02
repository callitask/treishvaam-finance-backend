package com.treishvaam.finance.service;

import com.treishvaam.finance.model.User;
import com.treishvaam.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        user.setVerificationCode(UUID.randomUUID().toString());
        user.setRoles(new HashSet<>());
        user.getRoles().add("USER");
        return userRepository.save(user);
    }

    public boolean verifyUser(String code) {
        Optional<User> optional = userRepository.findByVerificationCode(code);
        if (optional.isPresent()) {
            User user = optional.get();
            user.setEnabled(true);
            user.setVerificationCode(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
