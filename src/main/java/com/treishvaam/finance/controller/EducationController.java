package com.treishvaam.finance.controller;

import com.treishvaam.finance.model.EducationItem;
import com.treishvaam.finance.repository.EducationItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    @Autowired
    private EducationItemRepository educationItemRepository;

    @GetMapping
    public List<EducationItem> getAllEducationItems() {
        return educationItemRepository.findAll();
    }
}