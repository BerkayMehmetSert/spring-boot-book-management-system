package com.bms.bookmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String id;
    private String name;
    private String isbn;
    private String description;
    private Boolean isActive;
    private BookAuthorDto author;
    private BookPublisherDto publisher;
    private BookCategoryDto category;
}
