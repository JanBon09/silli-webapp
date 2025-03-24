package org.silli.sillibackend.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Comment {
    @Id
    private int id;
    
    private String content;
    private LocalDateTime createdAt;
    private Account author;
    private Post post;

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Account getAuthor() {
        return author;
    }

    public Post getPost() {
        return post;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
