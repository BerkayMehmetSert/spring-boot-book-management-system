package com.bms.bookmanagementsystem.exception;

import com.bms.bookmanagementsystem.exception.address.AddressAlreadyExistException;
import com.bms.bookmanagementsystem.exception.address.AddressNotFoundException;
import com.bms.bookmanagementsystem.exception.author.AuthorAlreadyExistException;
import com.bms.bookmanagementsystem.exception.author.AuthorNotFoundException;
import com.bms.bookmanagementsystem.exception.book.BookAlreadyExistException;
import com.bms.bookmanagementsystem.exception.book.BookNotFoundException;
import com.bms.bookmanagementsystem.exception.category.CategoryAlreadyExistException;
import com.bms.bookmanagementsystem.exception.category.CategoryNotFoundException;
import com.bms.bookmanagementsystem.exception.publisher.PublisherAlreadyExistException;
import com.bms.bookmanagementsystem.exception.publisher.PublisherNotFoundException;
import com.bms.bookmanagementsystem.helper.result.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(AddressAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResult handleAddressAlreadyExistException(AddressAlreadyExistException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult handleAddressNotFoundException(AddressNotFoundException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(AuthorAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResult handleAuthorAlreadyExistException(AuthorAlreadyExistException e) {
        return new ErrorResult(e.getMessage());
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult handleAuthorNotFoundException(AuthorNotFoundException e) {
        return new ErrorResult(e.getMessage());
    }

    @ExceptionHandler(BookAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResult handleBookAlreadyExistException(BookAlreadyExistException e) {
        return new ErrorResult(e.getMessage());
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult handleBookNotFoundException(BookNotFoundException e) {
        return new ErrorResult(e.getMessage());
    }

    @ExceptionHandler(CategoryAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResult handleCategoryAlreadyExistException(CategoryAlreadyExistException e) {
        return new ErrorResult(e.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult handleCategoryNotFoundException(CategoryNotFoundException e) {
        return new ErrorResult(e.getMessage());
    }

    @ExceptionHandler(PublisherAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResult handlePublisherAlreadyExistException(PublisherAlreadyExistException e) {
        return new ErrorResult(e.getMessage());
    }

    @ExceptionHandler(PublisherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult handlePublisherNotFoundException(PublisherNotFoundException e) {
        return new ErrorResult(e.getMessage());
    }
}
