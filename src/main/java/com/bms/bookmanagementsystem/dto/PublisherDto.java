package com.bms.bookmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDto {
    private String id;
    private String name;
    private String website;
    private Boolean isActive;
    private List<AddressDto> addressList;
    private List<PublisherBookDto> bookList;
}
