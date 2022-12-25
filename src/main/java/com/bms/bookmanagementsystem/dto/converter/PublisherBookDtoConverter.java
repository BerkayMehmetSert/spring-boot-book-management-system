package com.bms.bookmanagementsystem.dto.converter;

import com.bms.bookmanagementsystem.dto.PublisherBookDto;
import com.bms.bookmanagementsystem.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublisherBookDtoConverter {
    public PublisherBookDto convert(Book from) {
        return new PublisherBookDto(
                from.getId(),
                from.getName(),
                from.getIsbn(),
                from.getIsActive()
        );
    }

    public List<PublisherBookDto> convert(List<Book> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
