package com.treishvaam.finance.controller;

import com.treishvaam.finance.model.AuthRequest;
import com.treishvaam.finance.model.User;
import com.treishvaam.finance.model.UserRegistrationRequest;
import com.treishvaam.finance.security.JwtUtil;
import com.treishvaam.finance.service.EmailService;
import com.treishvaam.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest registrationRequest) {
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(registrationRequest.getPassword());
        user.setEmail(registrationRequest.getEmail());
        User registered = userService.registerUser(user);
        emailService.sendVerificationEmail(registered);
        return ResponseEntity.ok("User registered. Verification email sent.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String code) {
        boolean verified = userService.verifyUser(code);
        return verified ? ResponseEntity.ok("Verified!") : ResponseEntity.badRequest().body("Invalid code");
    }

    @GetMapping("/test") // This will make the endpoint accessible at /api/auth/test
    public String testEndpoint() {
        return "Backend is running and accessible!";
    }
}