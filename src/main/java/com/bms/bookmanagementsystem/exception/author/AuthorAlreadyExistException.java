package com.bms.bookmanagementsystem.exception.author;

public class AuthorAlreadyExistException extends RuntimeException {
    public AuthorAlreadyExistException(String message) {
        super(message);
    }
}
