package org.silli.sillibackend.models;

import org.springframework.data.annotation.Id;

public class AccountDto {
    @Id
    private int id;

    private String password;
    private String username;


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
}
