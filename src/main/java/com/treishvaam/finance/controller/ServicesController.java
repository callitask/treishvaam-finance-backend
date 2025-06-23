package com.treishvaam.finance.controller;

import com.treishvaam.finance.model.ServiceItem;
import com.treishvaam.finance.repository.ServiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServicesController {

    @Autowired
    private ServiceItemRepository serviceItemRepository;

    @GetMapping
    public List<ServiceItem> getAllServices() {
        return serviceItemRepository.findAll();
    }
}
