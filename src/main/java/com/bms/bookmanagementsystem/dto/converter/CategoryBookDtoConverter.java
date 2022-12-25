package com.bms.bookmanagementsystem.dto.converter;

import com.bms.bookmanagementsystem.dto.CategoryBookDto;
import com.bms.bookmanagementsystem.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryBookDtoConverter {
    public CategoryBookDto convert(Book from){
        return new CategoryBookDto(
                from.getId(),
                from.getName(),
                from.getIsbn(),
                from.getIsActive()
        );
    }

    public List<CategoryBookDto> convert(List<Book> from){
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
