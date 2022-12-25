package com.bms.bookmanagementsystem.service;

import com.bms.bookmanagementsystem.dto.BookDto;
import com.bms.bookmanagementsystem.dto.converter.BookDtoConverter;
import com.bms.bookmanagementsystem.dto.request.book.CreateBookRequest;
import com.bms.bookmanagementsystem.dto.request.book.UpdateBookRequest;
import com.bms.bookmanagementsystem.exception.book.BookAlreadyExistException;
import com.bms.bookmanagementsystem.exception.book.BookNotFoundException;
import com.bms.bookmanagementsystem.helper.BusinessMessage;
import com.bms.bookmanagementsystem.helper.DateHelper;
import com.bms.bookmanagementsystem.helper.GenerateIsbnNumber;
import com.bms.bookmanagementsystem.helper.LogMessage;
import com.bms.bookmanagementsystem.helper.result.DataResult;
import com.bms.bookmanagementsystem.helper.result.Result;
import com.bms.bookmanagementsystem.helper.result.SuccessDataResult;
import com.bms.bookmanagementsystem.helper.result.SuccessResult;
import com.bms.bookmanagementsystem.model.Book;
import com.bms.bookmanagementsystem.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;
    private final BookDtoConverter converter;

    public BookService(BookRepository bookRepository,
                       AuthorService authorService,
                       CategoryService categoryService,
                       PublisherService publisherService,
                       BookDtoConverter converter) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
        this.converter = converter;
    }

    // Create a new Book
    public Result createBook(CreateBookRequest request) {
        Book book = new Book();

        checkIfBookExistsByName(request.getName());
        book.setName(request.getName());
        book.setIsbn(GenerateIsbnNumber.generate());
        book.setDescription(request.getDescription());
        book.setAuthor(authorService.findAuthorByAuthorId(request.getAuthorId()));
        book.setPublisher(publisherService.findPublisherByPublisherId(request.getPublisherId()));
        book.setCategory(categoryService.findCategoryByCategoryId(request.getCategoryId()));
        book.setCreatedAt(DateHelper.getCurrentDateTime());
        book.setUpdatedAt(DateHelper.getCurrentDateTime());
        book.setIsActive(true);

        bookRepository.save(book);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_SAVED);

        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_SAVED);
    }

    // Update a Book
    public Result updateBook(String id, UpdateBookRequest request) {
        Book book = findBookByBookId(id);

        if (!book.getName().equals(request.getName())) {
            checkIfBookExistsByName(request.getName());
        }

        book.setName(request.getName());
        book.setDescription(request.getDescription());
        book.setAuthor(authorService.findAuthorByAuthorId(request.getAuthorId()));
        book.setPublisher(publisherService.findPublisherByPublisherId(request.getPublisherId()));
        book.setCategory(categoryService.findCategoryByCategoryId(request.getCategoryId()));
        book.setUpdatedAt(DateHelper.getCurrentDateTime());

        bookRepository.save(book);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_UPDATED);
        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_UPDATED);
    }

    // Delete a Book
    public Result deleteBook(String id) {
        Book book = findBookByBookId(id);

        book.setIsActive(false);
        book.setUpdatedAt(DateHelper.getCurrentDateTime());

        bookRepository.save(book);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_DELETED);
        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_DELETED);
    }

    // Get a Book by id
    public DataResult<BookDto> findBookById(String id) {
        Book book = findBookByBookId(id);

        log.info(LogMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
        return new SuccessDataResult<>(converter.convert(book),
                BusinessMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
    }

    // Get a Book by name
    public DataResult<BookDto> findBookByName(String name) {
        Book book = bookRepository.findByNameIgnoreCase(name).orElseThrow(() -> {
            log.error(LogMessage.Book.BOOK_NOT_FOUND);
            return new BookNotFoundException(BusinessMessage.Book.BOOK_NOT_FOUND);
        });

        checkIfBookIsActive(book);

        log.info(LogMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
        return new SuccessDataResult<>(converter.convert(book),
                BusinessMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
    }

    // Get a Book by isbn
    public DataResult<BookDto> findBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> {
            log.error(LogMessage.Book.BOOK_NOT_FOUND);
            return new BookNotFoundException(BusinessMessage.Book.BOOK_NOT_FOUND);
        });

        checkIfBookIsActive(book);

        log.info(LogMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
        return new SuccessDataResult<>(converter.convert(book),
                BusinessMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
    }

    // Get all Books
    public DataResult<List<BookDto>> findAllBooks() {
        List<Book> bookList = bookRepository.findAll();

        if (bookList.isEmpty()) {
            log.error(LogMessage.Book.BOOK_NOT_FOUND);
            throw new BookNotFoundException(BusinessMessage.Book.BOOK_NOT_FOUND);
        }

        log.info(LogMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
        return new SuccessDataResult<>(converter.convert(bookList),
                BusinessMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
    }

    // Check if book exists by name
    private void checkIfBookExistsByName(String name) {
        if (bookRepository.existsByNameIgnoreCase(name)) {
            log.error(LogMessage.Book.BOOK_ALREADY_EXISTS);
            throw new BookAlreadyExistException(BusinessMessage.Book.BOOK_ALREADY_EXISTS);
        }
    }

    // Find a Book by id
    private Book findBookByBookId(String id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            log.error(LogMessage.Book.BOOK_NOT_FOUND);
            throw new BookNotFoundException(BusinessMessage.Book.BOOK_NOT_FOUND);
        });

        checkIfBookIsActive(book);
        return book;
    }

    // Check if book is active
    private void checkIfBookIsActive(Book book) {
        if (!book.getIsActive()) {
            log.error(LogMessage.Book.BOOK_NOT_ACTIVE);
            throw new BookNotFoundException(BusinessMessage.Book.BOOK_NOT_ACTIVE);
        }
    }
}
