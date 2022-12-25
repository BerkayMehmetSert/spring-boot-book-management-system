package com.bms.bookmanagementsystem.controller;

import com.bms.bookmanagementsystem.dto.CategoryDto;
import com.bms.bookmanagementsystem.dto.request.category.CreateCategoryRequest;
import com.bms.bookmanagementsystem.dto.request.category.UpdateCategoryRequest;
import com.bms.bookmanagementsystem.helper.result.DataResult;
import com.bms.bookmanagementsystem.helper.result.Result;
import com.bms.bookmanagementsystem.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create a new category
    @PostMapping
    public Result createCategory(CreateCategoryRequest request) {
        return categoryService.createCategory(request);
    }

    // Update a category existing
    @PutMapping("/{id}")
    public Result updateCategory(@PathVariable String id, UpdateCategoryRequest request){
        return categoryService.updateCategory(id, request);
    }

    // Delete a category existing
    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable String id){
        return categoryService.deleteCategory(id);
    }

    // Get a category by id
    @GetMapping("/{id}")
    public DataResult<CategoryDto> findCategoryById(@PathVariable String id){
        return categoryService.findCategoryById(id);
    }

    // Get all categories
    @GetMapping
    public DataResult<List<CategoryDto>> findAllCategories(){
        return categoryService.findALlCategories();
    }
}
