package org.silli.sillibackend.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Post {
    @Id
    private int id;

    private String content;
    private LocalDateTime createdAt;
    private Account author;
    private Iterable<Comment> comments;

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

    public Iterable<Comment> getComments() {
        return comments;
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

    public void setComments(Iterable<Comment> comments) {
        this.comments = comments;
    }
}
