package com.treishvaam.finance.controller;

import com.treishvaam.finance.model.ContactMessage;
import com.treishvaam.finance.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @PostMapping
    public ResponseEntity<String> submitContactForm(@RequestBody ContactMessage message) {
        contactMessageRepository.save(message);
        return ResponseEntity.ok("Message received successfully!");
    }
}
