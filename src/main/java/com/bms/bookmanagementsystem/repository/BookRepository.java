package com.bms.bookmanagementsystem.repository;

import com.bms.bookmanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}