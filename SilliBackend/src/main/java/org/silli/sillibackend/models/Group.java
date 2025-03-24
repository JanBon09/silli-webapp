package org.silli.sillibackend.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Group {
    @Id
    private int id;

    private String name;
    private LocalDateTime createdAt;
    private Account account;
    private Iterable<Account> members;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Account getAccount() {
        return account;
    }

    public Iterable<Account> getMembers() {
        return members;
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

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setMembers(Iterable<Account> members) {
        this.members = members;
    }
}
