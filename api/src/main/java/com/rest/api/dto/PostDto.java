package com.rest.api.dto;

public class PostDto {

    private String description;

    public PostDto(String description) {

        this.description = description;

    }

    public PostDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
