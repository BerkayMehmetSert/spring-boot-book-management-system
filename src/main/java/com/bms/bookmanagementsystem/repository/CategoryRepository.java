package com.bms.bookmanagementsystem.repository;

import com.bms.bookmanagementsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsByName(String name);

    List<Category> findAllByIsActive(boolean isActive);
}