package com.bms.bookmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private String id;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private List<AuthorBookDto> bookList;
}
