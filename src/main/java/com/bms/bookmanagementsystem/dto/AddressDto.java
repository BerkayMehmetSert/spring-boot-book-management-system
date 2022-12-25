package com.bms.bookmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private Boolean isActive;
}
