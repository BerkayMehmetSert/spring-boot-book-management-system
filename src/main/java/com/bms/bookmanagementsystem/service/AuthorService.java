package com.bms.bookmanagementsystem.service;

import com.bms.bookmanagementsystem.dto.AuthorDto;
import com.bms.bookmanagementsystem.dto.converter.AuthorDtoConverter;
import com.bms.bookmanagementsystem.dto.request.author.CreateAuthorRequest;
import com.bms.bookmanagementsystem.dto.request.author.UpdateAuthorRequest;
import com.bms.bookmanagementsystem.exception.author.AuthorAlreadyExistException;
import com.bms.bookmanagementsystem.exception.author.AuthorNotFoundException;
import com.bms.bookmanagementsystem.helper.BusinessMessage;
import com.bms.bookmanagementsystem.helper.DateHelper;
import com.bms.bookmanagementsystem.helper.LogMessage;
import com.bms.bookmanagementsystem.helper.result.DataResult;
import com.bms.bookmanagementsystem.helper.result.Result;
import com.bms.bookmanagementsystem.helper.result.SuccessDataResult;
import com.bms.bookmanagementsystem.helper.result.SuccessResult;
import com.bms.bookmanagementsystem.model.Author;
import com.bms.bookmanagementsystem.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorDtoConverter converter;

    public AuthorService(AuthorRepository authorRepository,
                         AuthorDtoConverter converter) {
        this.authorRepository = authorRepository;
        this.converter = converter;
    }

    // Create Author
    public Result createAuthor(CreateAuthorRequest request) {
        Author author = new Author();
        checkIfAuthorExists(request.getFirstName(), request.getLastName());
        author.setFirstName(request.getFirstName());
        author.setLastName(request.getLastName());
        author.setIsActive(true);
        author.setCreatedAt(DateHelper.getCurrentDateTime());
        author.setUpdatedAt(DateHelper.getCurrentDateTime());

        authorRepository.save(author);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_SAVED);
        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_SAVED);
    }

    // Update Author
    public Result updateAuthor(String id, UpdateAuthorRequest request) {
        Author author = findAuthorByAuthorId(id);
        checkIfAuthorExists(request.getFirstName(), request.getLastName());

        author.setFirstName(request.getFirstName());
        author.setLastName(request.getLastName());
        author.setUpdatedAt(DateHelper.getCurrentDateTime());

        authorRepository.save(author);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_UPDATED);
        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_UPDATED);
    }

    // Delete Author
    public Result deleteAuthor(String id) {
        Author author = findAuthorByAuthorId(id);
        author.setIsActive(false);
        author.setDeletedAt(DateHelper.getCurrentDateTime());

        authorRepository.save(author);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_DELETED);
        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_DELETED);
    }

    // Get author by id
    public DataResult<AuthorDto> findAuthorById(String id) {
        Author author = findAuthorByAuthorId(id);

        log.info(LogMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
        return new SuccessDataResult<>(converter.convert(author),
                BusinessMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
    }

    // Get all authors
    public DataResult<List<AuthorDto>> findAllAuthors() {
        List<Author> authors = authorRepository.findAll();

        if (authors.isEmpty()) {
            log.info(LogMessage.Author.AUTHOR_NOT_FOUND);
            throw new AuthorNotFoundException(BusinessMessage.Author.AUTHOR_NOT_FOUND);
        }

        log.info(LogMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
        return new SuccessDataResult<>(converter.convert(authors),
                BusinessMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
    }

    // Check if Author exists by first name and last name
    private void checkIfAuthorExists(String firstName, String lastName) {
        if (authorRepository.existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName)) {
            log.error(LogMessage.Author.AUTHOR_ALREADY_EXISTS);
            throw new AuthorAlreadyExistException(BusinessMessage.Author.AUTHOR_ALREADY_EXISTS);
        }
    }

    // Find Author by id
    protected Author findAuthorByAuthorId(String id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> {
            log.error(LogMessage.Author.AUTHOR_NOT_FOUND);
            throw new AuthorAlreadyExistException(BusinessMessage.Author.AUTHOR_NOT_FOUND);
        });

        if (!author.getIsActive()) {
            log.error(LogMessage.Author.AUTHOR_NOT_FOUND);
            throw new AuthorAlreadyExistException(BusinessMessage.Author.AUTHOR_NOT_FOUND);
        }

        return author;
    }
}
