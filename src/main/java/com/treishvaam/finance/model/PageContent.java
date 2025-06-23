package com.treishvaam.finance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class PageContent {

    @Id
    @Column(unique = true, nullable = false)
    private String pageName; // e.g., "about", "vision", "index"
    private String title;
    @Lob
    private String content;

    public PageContent() {}

    public String getPageName() { return pageName; }
    public void setPageName(String pageName) { this.pageName = pageName; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
