package com.bms.bookmanagementsystem.service;

import com.bms.bookmanagementsystem.dto.AddressDto;
import com.bms.bookmanagementsystem.dto.converter.AddressDtoConverter;
import com.bms.bookmanagementsystem.dto.request.address.CreateAddressRequest;
import com.bms.bookmanagementsystem.dto.request.address.UpdateAddressRequest;
import com.bms.bookmanagementsystem.helper.BusinessMessage;
import com.bms.bookmanagementsystem.helper.LogMessage;
import com.bms.bookmanagementsystem.helper.result.DataResult;
import com.bms.bookmanagementsystem.helper.result.Result;
import com.bms.bookmanagementsystem.helper.result.SuccessDataResult;
import com.bms.bookmanagementsystem.helper.result.SuccessResult;
import com.bms.bookmanagementsystem.model.Address;
import com.bms.bookmanagementsystem.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddressService {
    private final AddressRepository addressRepository;
    private final PublisherService publisherService;
    private final AddressDtoConverter converter;

    public AddressService(AddressRepository addressRepository,
                          PublisherService publisherService,
                          AddressDtoConverter converter) {
        this.addressRepository = addressRepository;
        this.publisherService = publisherService;
        this.converter = converter;
    }

    // Create Address
    public Result createAddress(CreateAddressRequest request) {
        Address address = new Address();
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setZipCode(request.getZipCode());
        address.setIsActive(true);
        address.setPublisher(publisherService.findPublisherByPublisherId(request.getPublisherId()));

        addressRepository.save(address);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_SAVED);
        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_SAVED);
    }

    // Update Address
    public Result updateAddress(String id, UpdateAddressRequest request) {
        Address address = findAddressByAddressId(id);
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setZipCode(request.getZipCode());
        address.setPublisher(publisherService.findPublisherByPublisherId(request.getPublisherId()));

        addressRepository.save(address);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_UPDATED);
        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_UPDATED);
    }

    // Delete Address
    public Result deleteAddress(String id) {
        Address address = findAddressByAddressId(id);
        address.setIsActive(false);

        addressRepository.save(address);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_DELETED);
        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_DELETED);
    }

    // Get Address By id
    public DataResult<AddressDto> findAddressById(String id) {
        Address address = findAddressByAddressId(id);

        log.info(LogMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
        return new SuccessDataResult<>(converter.convert(address),
                BusinessMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
    }

    // Get all Address
    public DataResult<List<AddressDto>> findAllAddress() {
        List<Address> addressList = addressRepository.findAll();

        if (addressList.isEmpty()) {
            log.info(LogMessage.Address.ADDRESS_NOT_FOUND);
            return new SuccessDataResult<>(BusinessMessage.Address.ADDRESS_NOT_FOUND);
        }

        log.info(LogMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
        return new SuccessDataResult<>(converter.convert(addressList),
                BusinessMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
    }

    // Find Address By id
    protected Address findAddressByAddressId(String id) {
        return addressRepository.findById(id).orElseThrow(() -> {
            log.error(LogMessage.Address.ADDRESS_NOT_FOUND);
            throw new RuntimeException(BusinessMessage.Address.ADDRESS_NOT_FOUND);
        });
    }
}
