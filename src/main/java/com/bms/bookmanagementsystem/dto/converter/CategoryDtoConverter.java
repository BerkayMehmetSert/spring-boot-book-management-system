package com.bms.bookmanagementsystem.dto.converter;

import com.bms.bookmanagementsystem.dto.CategoryDto;
import com.bms.bookmanagementsystem.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryDtoConverter {
    private final CategoryBookDtoConverter categoryBookDtoConverter;

    public CategoryDtoConverter(CategoryBookDtoConverter categoryBookDtoConverter) {
        this.categoryBookDtoConverter = categoryBookDtoConverter;
    }

    public CategoryDto convert(Category from) {
        return new CategoryDto(
                from.getId(),
                from.getName(),
                from.getIsActive(),
                categoryBookDtoConverter.convert(from.getBookList())
        );
    }

    public List<CategoryDto> convert(List<Category> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
