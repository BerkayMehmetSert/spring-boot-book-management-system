package com.bms.bookmanagementsystem.dto.converter;

import com.bms.bookmanagementsystem.dto.BookAuthorDto;
import com.bms.bookmanagementsystem.model.Author;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookAuthorDtoConverter {
    public BookAuthorDto convert(Author from){
        return new BookAuthorDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getIsActive()
        );
    }

    public List<BookAuthorDto> convert(List<Author> from){
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
