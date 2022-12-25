package com.bms.bookmanagementsystem.dto.converter;

import com.bms.bookmanagementsystem.dto.BookDto;
import com.bms.bookmanagementsystem.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookDtoConverter {
    private final BookAuthorDtoConverter bookAuthorDtoConverter;
    private final BookPublisherDtoConverter bookPublisherDtoConverter;
    private final BookCategoryDtoConverter bookCategoryDtoConverter;

    public BookDtoConverter(BookAuthorDtoConverter bookAuthorDtoConverter,
                            BookPublisherDtoConverter bookPublisherDtoConverter,
                            BookCategoryDtoConverter bookCategoryDtoConverter) {
        this.bookAuthorDtoConverter = bookAuthorDtoConverter;
        this.bookPublisherDtoConverter = bookPublisherDtoConverter;
        this.bookCategoryDtoConverter = bookCategoryDtoConverter;
    }

    public BookDto convert(Book from){
        return new BookDto(
                from.getId(),
                from.getName(),
                from.getIsbn(),
                from.getDescription(),
                from.getIsActive(),
                bookAuthorDtoConverter.convert(from.getAuthor()),
                bookPublisherDtoConverter.convert(from.getPublisher()),
                bookCategoryDtoConverter.convert(from.getCategory())
        );
    }

    public List<BookDto> convert(List<Book> from){
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
