package com.treishvaam.finance.controller;

import com.treishvaam.finance.model.BlogPost;
import com.treishvaam.finance.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @GetMapping("/posts")
    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }
}
