package com.rest.api.dto;

public class AddressDto {
    private String address;

    public AddressDto() {}

    public AddressDto(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
