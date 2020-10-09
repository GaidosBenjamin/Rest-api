package com.rest.impl.domain.mapper.adress.api;

import com.rest.api.dto.AddressDto;
import com.rest.impl.dao.address.AddressDao;

public interface AddressMapper {
    AddressDto toAddressDto(AddressDao addressDao);

    AddressDao toAddressDao(AddressDto addressDto);
}
