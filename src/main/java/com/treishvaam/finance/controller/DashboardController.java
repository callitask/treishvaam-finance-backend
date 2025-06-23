package com.treishvaam.finance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @GetMapping("/data")
    public ResponseEntity<String> getDashboardData(Principal principal) {
        String welcomeMessage = "Welcome to your dashboard, " + principal.getName() + "!";
        return ResponseEntity.ok(welcomeMessage);
    }
}
