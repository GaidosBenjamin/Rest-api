package com.rest.impl.exceptions;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(Integer id) {
        super("Could not find group with id: " + id);
    }
}
