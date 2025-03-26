package org.silli.sillibackend.models;

import org.springframework.data.annotation.Id;

public class PostDto implements ManipulableEntity{
    @Id
    private int id;

    private String content;
    private int accountId;

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
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

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
