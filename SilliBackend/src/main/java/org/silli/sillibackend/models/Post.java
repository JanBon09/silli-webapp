package org.silli.sillibackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

public class Post {
    @Id
    private int id;

    private String content;

    @Column("createdat")
    private LocalDateTime createdAt;
    @Column("account_id")
    private int accountId;

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getAccountId() {
        return accountId;
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

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
