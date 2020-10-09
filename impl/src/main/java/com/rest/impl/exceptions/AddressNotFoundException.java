package com.rest.impl.exceptions;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Integer id) {
        super("Could not find address for user with id: " + id);
    }
}
