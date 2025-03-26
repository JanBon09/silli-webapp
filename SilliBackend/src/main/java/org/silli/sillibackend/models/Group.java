package org.silli.sillibackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("social_group")
public class Group {
    @Id
    private int id;

    private String name;
    @Column("createdat")
    private LocalDateTime createdAt;
    private String accessibility;
    @Column("account_id")
    private int accountId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

}
