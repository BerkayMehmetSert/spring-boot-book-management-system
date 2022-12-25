package com.bms.bookmanagementsystem.dto.request.address;

import lombok.Data;

@Data
public class CreateAddressRequest {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String publisherId;
}
