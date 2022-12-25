package com.bms.bookmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAuthorDto {
    private String id;
    private String firstName;
    private String lastName;
    private Boolean isActive;
}
