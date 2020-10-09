package com.rest.impl.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("Could not found user with id: " + id);
    }
}
