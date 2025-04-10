package org.silli.sillibackend.models;

import org.springframework.data.annotation.Id;

public class Account {
    @Id
    private int id;

    private String username;
    private String password;
    private int enabled;
    private String authority;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
