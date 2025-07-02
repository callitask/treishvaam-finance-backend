package com.treishvaam.finance.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping
    public List<Content> getAllContent() {
        return contentService.getAllContent();
    }

    @PostMapping
    public Content createContent(@RequestBody Content content) {
        return contentService.createContent(content);
    }

    @GetMapping("/{id}")
    public Content getContent(@PathVariable Long id) {
        return contentService.getContent(id);
    }

    @PutMapping("/{id}")
    public Content updateContent(@PathVariable Long id, @RequestBody Content content) {
        return contentService.updateContent(id, content);
    }

    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
    }
}
