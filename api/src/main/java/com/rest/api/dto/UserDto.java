package com.rest.api.dto;

import net.minidev.json.annotate.JsonIgnore;

public class UserDto {

    private String name;
    private String age;

    public UserDto(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
