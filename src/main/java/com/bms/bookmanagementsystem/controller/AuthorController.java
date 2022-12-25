package com.bms.bookmanagementsystem.controller;

import com.bms.bookmanagementsystem.dto.AuthorDto;
import com.bms.bookmanagementsystem.dto.request.author.CreateAuthorRequest;
import com.bms.bookmanagementsystem.dto.request.author.UpdateAuthorRequest;
import com.bms.bookmanagementsystem.helper.result.DataResult;
import com.bms.bookmanagementsystem.helper.result.Result;
import com.bms.bookmanagementsystem.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // Create Author
    @PostMapping
    public Result createAuthor(CreateAuthorRequest request) {
        return authorService.createAuthor(request);
    }

    // Update Author
    @PutMapping("/{id}")
    public Result updateAuthor(@PathVariable String id, UpdateAuthorRequest request) {
        return authorService.updateAuthor(id, request);
    }

    // Delete Author
    @DeleteMapping("/{id}")
    public Result deleteAuthor(@PathVariable String id) {
        return authorService.deleteAuthor(id);
    }

    // Get Author By id
    @GetMapping("/{id}")
    public DataResult<AuthorDto> findAuthorById(@PathVariable String id) {
        return authorService.findAuthorById(id);
    }

    // Get All Authors
    @GetMapping
    public DataResult<List<AuthorDto>> findAllAuthors() {
        return authorService.findAllAuthors();
    }
}
