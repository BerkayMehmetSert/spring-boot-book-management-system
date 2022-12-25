package com.bms.bookmanagementsystem.repository;

import com.bms.bookmanagementsystem.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, String> {
}