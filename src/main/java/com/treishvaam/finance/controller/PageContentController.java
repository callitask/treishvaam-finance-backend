package com.treishvaam.finance.controller;

import com.treishvaam.finance.model.PageContent;
import com.treishvaam.finance.repository.PageContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pages")
public class PageContentController {

    @Autowired
    private PageContentRepository pageContentRepository;

    @GetMapping("/{pageName}")
    public ResponseEntity<PageContent> getPageContent(@PathVariable String pageName) {
        return pageContentRepository.findById(pageName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
