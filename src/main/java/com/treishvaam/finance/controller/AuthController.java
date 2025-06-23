package com.treishvaam.finance.controller;

import com.treishvaam.finance.model.User;
import com.treishvaam.finance.repository.UserRepository;
import com.treishvaam.finance.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
// REMOVED: @RequestMapping("/api/auth") from here
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/api/auth/check-username") // UPDATED: Full path here
    public ResponseEntity<?> checkUsername(@RequestBody Map<String, String> payload) {
        boolean exists = userRepository.findByUsername(payload.get("username")).isPresent();
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    @PostMapping("/api/auth/register") // UPDATED: Full path here
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/api/auth/login") // UPDATED: Full path here
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User loginRequest) throws Exception {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(Map.of("token", jwt));
    }
}