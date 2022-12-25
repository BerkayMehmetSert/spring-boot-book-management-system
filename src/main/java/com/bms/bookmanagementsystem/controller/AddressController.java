package com.bms.bookmanagementsystem.controller;

import com.bms.bookmanagementsystem.dto.AddressDto;
import com.bms.bookmanagementsystem.dto.request.address.CreateAddressRequest;
import com.bms.bookmanagementsystem.dto.request.address.UpdateAddressRequest;
import com.bms.bookmanagementsystem.helper.result.DataResult;
import com.bms.bookmanagementsystem.helper.result.Result;
import com.bms.bookmanagementsystem.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Create Address
    @PostMapping
    public Result createAddress(CreateAddressRequest request){
        return addressService.createAddress(request);
    }

    // Update Address
    @PutMapping("/{id}")
    public Result updateAddress(@PathVariable String id, UpdateAddressRequest request){
        return addressService.updateAddress(id, request);
    }

    // Delete Address
    @DeleteMapping("/{id}")
    public Result deleteAddress(@PathVariable String id){
        return addressService.deleteAddress(id);
    }

    // Get address by id
    @GetMapping("/{id}")
    public DataResult<AddressDto> findAddressById(@PathVariable String id){
        return addressService.findAddressById(id);
    }

    // Get all addresses
    @GetMapping
    public DataResult<List<AddressDto>> findAllAddresses(){
        return addressService.findAllAddress();
    }
}
