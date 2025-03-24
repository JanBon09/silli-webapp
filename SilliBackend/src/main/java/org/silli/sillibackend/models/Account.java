package org.silli.sillibackend.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

public class Account {
    @Id
    private int id;

    private String username;
    private String password;
    private int enabled;
    private String authority;

    private Iterable<Post> posts;
    private Iterable<Comment> comments;
    private Iterable<Group> groups;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Iterable<Post> getPosts() {
        return posts;
    }

    public Iterable<Comment> getComments() {
        return comments;
    }

    public Iterable<Group> getGroups() {
        return groups;
    }

    public int getEnabled() {
        return enabled;
    }

    public String getAuthority() {
        return authority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPosts(Iterable<Post> posts) {
        this.posts = posts;
    }

    public void setComments(Iterable<Comment> comments) {
        this.comments = comments;
    }

    public void setGroups(Iterable<Group> groups) {
        this.groups = groups;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
