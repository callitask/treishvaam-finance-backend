package com.treishvaam.finance.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {
    @Autowired
    private ContentRepository contentRepository;

    public List<Content> getAllContent() {
        return contentRepository.findAll();
    }

    public Content createContent(Content content) {
        return contentRepository.save(content);
    }

    public Content getContent(Long id) {
        return contentRepository.findById(id).orElse(null);
    }

    public Content updateContent(Long id, Content updatedContent) {
        Optional<Content> optional = contentRepository.findById(id);
        if (optional.isPresent()) {
            Content content = optional.get();
            content.setTitle(updatedContent.getTitle());
            content.setBody(updatedContent.getBody());
            return contentRepository.save(content);
        }
        return null;
    }

    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }
}
