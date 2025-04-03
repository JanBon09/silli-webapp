package org.silli.sillibackend.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class PostDto {
    @Id
    private int id;

    private String content;
    private LocalDateTime createdAt;
    private String username;

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {return createdAt;}

    public String getUsername() {return username;}

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public void setUsername(String username) {this.username = username;}
}
