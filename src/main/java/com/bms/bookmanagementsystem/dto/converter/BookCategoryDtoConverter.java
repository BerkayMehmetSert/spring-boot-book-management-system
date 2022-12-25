package com.bms.bookmanagementsystem.dto.converter;

import com.bms.bookmanagementsystem.dto.BookCategoryDto;
import com.bms.bookmanagementsystem.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCategoryDtoConverter {
    public BookCategoryDto convert(Category from) {
        return new BookCategoryDto(
                from.getId(),
                from.getName(),
                from.getIsActive()
        );
    }

    public List<BookCategoryDto> convert(List<Category> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
