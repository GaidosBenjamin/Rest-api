package com.rest.impl.domain.mapper.adress.impl;

import com.rest.api.dto.AddressDto;
import com.rest.impl.dao.address.AddressDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class AddressMapperImplTest {

    private AddressMapperImpl addressMapper;

    @Before
    public void setUp() {
        addressMapper = new AddressMapperImpl();
    }

    @Test
    public void withGivenAddressDao_shouldReturnAddressDto() {
        AddressDto actual = addressMapper.toAddressDto(new AddressDao("Text"));

        assertThat(actual.getAddress()).isEqualTo("Text");
    }

    @Test
    public void withGivenAddresDto_shouldReturnAddressDao() {
        AddressDao actual = addressMapper.toAddressDao(new AddressDto("Text"));

        assertThat(actual.getAddress()).isEqualTo("Text");
    }

}