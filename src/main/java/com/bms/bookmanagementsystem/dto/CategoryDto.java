package com.bms.bookmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String id;
    private String name;
    private Boolean isActive;
    private List<CategoryBookDto> bookList;
}
