package com.bms.bookmanagementsystem.dto.converter;

import com.bms.bookmanagementsystem.dto.BookPublisherDto;
import com.bms.bookmanagementsystem.model.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookPublisherDtoConverter {
    public BookPublisherDto convert(Publisher from) {
        return new BookPublisherDto(
                from.getId(),
                from.getName(),
                from.getWebsite(),
                from.getIsActive()
        );
    }

    public List<BookPublisherDto> convert(List<Publisher> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
