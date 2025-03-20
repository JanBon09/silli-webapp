package org.silli.sillibackend.models;

import org.springframework.data.annotation.Id;

public class Account {
    @Id
    private int id;

    private String username;
    private String password;
}
