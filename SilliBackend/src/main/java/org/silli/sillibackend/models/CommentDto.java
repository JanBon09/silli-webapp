package org.silli.sillibackend.models;

import org.springframework.data.annotation.Id;

public class CommentDto implements ManipulableEntity{
    @Id
    private int id;

    private String content;
    private int accountId;
    private int postId;

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getAccountId(){
        return accountId;
    }

    public int getPostId(){
        return postId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatorId(int accountId) {
        this.accountId = accountId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
