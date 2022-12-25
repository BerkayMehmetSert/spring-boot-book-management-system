package com.bms.bookmanagementsystem.dto.converter;

import com.bms.bookmanagementsystem.dto.AddressDto;
import com.bms.bookmanagementsystem.model.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressDtoConverter {
    public AddressDto convert(Address from){
        return new AddressDto(
                from.getId(),
                from.getStreet(),
                from.getCity(),
                from.getState(),
                from.getZipCode(),
                from.getIsActive()
        );
    }

    public List<AddressDto> convert(List<Address> from){
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
