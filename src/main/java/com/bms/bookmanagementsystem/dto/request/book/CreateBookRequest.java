package com.bms.bookmanagementsystem.dto.request.book;

import lombok.Data;

@Data
public class CreateBookRequest {
    private String name;
    private String description;
    private String authorId;
    private String publisherId;
    private String categoryId;
}
