package com.rest.api.dto;

public class GroupDto {
    private String name;

    public GroupDto(String name) {
        this.name = name;
    }

    public GroupDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
