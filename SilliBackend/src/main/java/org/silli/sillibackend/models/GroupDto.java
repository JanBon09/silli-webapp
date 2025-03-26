package org.silli.sillibackend.models;

import org.springframework.data.annotation.Id;

public class GroupDto {
    @Id
    private int id;

    private String name;
    private String accessibility;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }
}
