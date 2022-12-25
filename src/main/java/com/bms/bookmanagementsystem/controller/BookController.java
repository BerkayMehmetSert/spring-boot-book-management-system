package com.bms.bookmanagementsystem.controller;

import com.bms.bookmanagementsystem.dto.BookDto;
import com.bms.bookmanagementsystem.dto.request.book.CreateBookRequest;
import com.bms.bookmanagementsystem.dto.request.book.UpdateBookRequest;
import com.bms.bookmanagementsystem.helper.result.DataResult;
import com.bms.bookmanagementsystem.helper.result.Result;
import com.bms.bookmanagementsystem.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Create a new Book
    @PostMapping
    public Result createBook(CreateBookRequest request) {
        return bookService.createBook(request);
    }

    // Update a Book
    @PutMapping("/{id}")
    public Result updateBook(@PathVariable String id, UpdateBookRequest request) {
        return bookService.updateBook(id, request);
    }

    // Delete a Book
    @DeleteMapping("/{id}")
    public Result deleteBook(@PathVariable String id) {
        return bookService.deleteBook(id);
    }

    // Get a book by id
    @GetMapping("/{id}")
    public DataResult<BookDto> findBookById(@PathVariable String id) {
        return bookService.findBookById(id);
    }

    // Get a book by name
    @GetMapping("/name/{name}")
    public DataResult<BookDto> findBookByName(@PathVariable String name) {
        return bookService.findBookByName(name);
    }

    // Get a book by isbn
    @GetMapping("/isbn/{isbn}")
    public DataResult<BookDto> findBookByIsbn(@PathVariable String isbn) {
        return bookService.findBookByIsbn(isbn);
    }

    // Get all books
    @GetMapping
    public DataResult<List<BookDto>> findAllBooks() {
        return bookService.findAllBooks();
    }
}
