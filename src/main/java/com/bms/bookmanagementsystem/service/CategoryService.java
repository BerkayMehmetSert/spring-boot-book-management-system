package com.bms.bookmanagementsystem.service;

import com.bms.bookmanagementsystem.dto.CategoryDto;
import com.bms.bookmanagementsystem.dto.converter.CategoryDtoConverter;
import com.bms.bookmanagementsystem.dto.request.category.CreateCategoryRequest;
import com.bms.bookmanagementsystem.dto.request.category.UpdateCategoryRequest;
import com.bms.bookmanagementsystem.exception.category.CategoryAlreadyExistException;
import com.bms.bookmanagementsystem.exception.category.CategoryNotFoundException;
import com.bms.bookmanagementsystem.helper.BusinessMessage;
import com.bms.bookmanagementsystem.helper.DateHelper;
import com.bms.bookmanagementsystem.helper.LogMessage;
import com.bms.bookmanagementsystem.helper.result.DataResult;
import com.bms.bookmanagementsystem.helper.result.Result;
import com.bms.bookmanagementsystem.helper.result.SuccessDataResult;
import com.bms.bookmanagementsystem.helper.result.SuccessResult;
import com.bms.bookmanagementsystem.model.Category;
import com.bms.bookmanagementsystem.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter converter;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryDtoConverter converter) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
    }

    // Create a new category
    public Result createCategory(CreateCategoryRequest request) {
        Category category = new Category();
        checkIfCategoryExistsByName(request.getName());

        category.setName(request.getName());
        category.setIsActive(true);
        category.setCreatedAt(DateHelper.getCurrentDateTime());
        category.setUpdatedAt(DateHelper.getCurrentDateTime());

        categoryRepository.save(category);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_SAVED);
        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_SAVED);
    }

    // Update a category existing
    public Result updateCategory(String id, UpdateCategoryRequest request) {
        Category category = findCategoryByCategoryId(id);
        checkIfCategoryExistsByName(request.getName());

        category.setName(request.getName());
        category.setUpdatedAt(DateHelper.getCurrentDateTime());

        categoryRepository.save(category);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_UPDATED);
        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_UPDATED);
    }

    // Delete a category existing
    public Result deleteCategory(String id) {
        Category category = findCategoryByCategoryId(id);
        category.setIsActive(false);
        category.setDeletedAt(DateHelper.getCurrentDateTime());

        categoryRepository.save(category);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_DELETED);
        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_DELETED);
    }

    // Get a category by id
    public DataResult<CategoryDto> findCategoryById(String id) {
        Category category = findCategoryByCategoryId(id);

        log.info(LogMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
        return new SuccessDataResult<>(converter.convert(category),
                BusinessMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
    }

    // Get all categories
    public DataResult<List<CategoryDto>> findALlCategories() {
        List<Category> categoryList = categoryRepository.findAllByIsActive(true);

        if (categoryList.isEmpty()) {
            log.info(LogMessage.Category.CATEGORY_NOT_FOUND);
            throw new CategoryNotFoundException(BusinessMessage.Category.CATEGORY_NOT_FOUND);
        }

        log.info(LogMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
        return new SuccessDataResult<>(converter.convert(categoryList),
                BusinessMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
    }

    // Find category by id
    protected Category findCategoryByCategoryId(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> {
            log.error(LogMessage.Category.CATEGORY_NOT_FOUND);
            throw new CategoryNotFoundException(BusinessMessage.Category.CATEGORY_NOT_FOUND);
        });

        if (!category.getIsActive()) {
            log.error(LogMessage.Category.CATEGORY_NOT_ACTIVE);
            throw new CategoryNotFoundException(BusinessMessage.Category.CATEGORY_NOT_ACTIVE);
        }

        return category;
    }

    // Check if category exists by name
    private void checkIfCategoryExistsByName(String name) {
        if (categoryRepository.existsByName(name)) {
            log.error(LogMessage.Category.CATEGORY_ALREADY_EXISTS);
            throw new CategoryAlreadyExistException(BusinessMessage.Category.CATEGORY_ALREADY_EXISTS);
        }
    }

}
