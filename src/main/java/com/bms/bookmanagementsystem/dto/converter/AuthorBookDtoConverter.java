package com.bms.bookmanagementsystem.dto.converter;

import com.bms.bookmanagementsystem.dto.AuthorBookDto;
import com.bms.bookmanagementsystem.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorBookDtoConverter {
    public AuthorBookDto convert(Book from){
        return new AuthorBookDto(
                from.getId(),
                from.getName(),
                from.getIsbn(),
                from.getIsActive()
        );
    }

    public List<AuthorBookDto> convert(List<Book> from){
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
