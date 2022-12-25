package com.bms.bookmanagementsystem.dto.converter;

import com.bms.bookmanagementsystem.dto.AuthorDto;
import com.bms.bookmanagementsystem.model.Author;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorDtoConverter {
    private final AuthorBookDtoConverter authorBookDtoConverter;

    public AuthorDtoConverter(AuthorBookDtoConverter authorBookDtoConverter) {
        this.authorBookDtoConverter = authorBookDtoConverter;
    }

    public AuthorDto convert(Author from) {
        return new AuthorDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getIsActive(),
                authorBookDtoConverter.convert(from.getBookList())
        );
    }

    public List<AuthorDto> convert(List<Author> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
