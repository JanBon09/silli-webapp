package org.silli.sillibackend.models;

import org.springframework.data.annotation.Id;

public class CommentDto {
    @Id
    private int id;

    private String content;

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
