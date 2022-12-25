package com.bms.bookmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPublisherDto {
    private String id;
    private String name;
    private String website;
    private Boolean isActive;
}
