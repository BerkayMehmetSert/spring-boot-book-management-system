package com.bms.bookmanagementsystem.dto.converter;

import com.bms.bookmanagementsystem.dto.PublisherDto;
import com.bms.bookmanagementsystem.model.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublisherDtoConverter {
    private final AddressDtoConverter addressDtoConverter;
    private final PublisherBookDtoConverter publisherBookDtoConverter;

    public PublisherDtoConverter(AddressDtoConverter addressDtoConverter,
                                 PublisherBookDtoConverter publisherBookDtoConverter) {
        this.addressDtoConverter = addressDtoConverter;
        this.publisherBookDtoConverter = publisherBookDtoConverter;
    }

    public PublisherDto convert(Publisher from) {
        return new PublisherDto(
                from.getId(),
                from.getName(),
                from.getWebsite(),
                from.getIsActive(),
                addressDtoConverter.convert(from.getAddressList()),
                publisherBookDtoConverter.convert(from.getBookList())
        );
    }

    public List<PublisherDto> convert(List<Publisher> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
