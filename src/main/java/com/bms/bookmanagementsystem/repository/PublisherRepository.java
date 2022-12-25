package com.bms.bookmanagementsystem.repository;

import com.bms.bookmanagementsystem.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, String> {
    boolean existsByNameIgnoreCase(String name);

    List<Publisher> findAllByIsActive(boolean b);
}