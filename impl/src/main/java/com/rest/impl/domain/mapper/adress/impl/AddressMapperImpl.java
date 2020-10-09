package com.rest.impl.domain.mapper.adress.impl;

import com.rest.api.dto.AddressDto;
import com.rest.impl.dao.address.AddressDao;
import com.rest.impl.domain.mapper.adress.api.AddressMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperImpl implements AddressMapper {
    @Override
    public AddressDto toAddressDto(AddressDao addressDao) {
        return new AddressDto(addressDao.getAddress());
    }

    @Override
    public AddressDao toAddressDao(AddressDto addressDto) {
        AddressDao address = new AddressDao();
        address.setAddress(addressDto.getAddress());
        return address;
    }
}
