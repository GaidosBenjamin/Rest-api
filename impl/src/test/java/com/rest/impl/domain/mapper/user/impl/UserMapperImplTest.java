package com.rest.impl.domain.mapper.user.impl;

import com.rest.api.dto.UserDto;
import com.rest.impl.dao.UserDao;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class UserMapperImplTest {

    private UserMapperImpl userMapper;

    @Before
    public void setUp() {
        userMapper = new UserMapperImpl();
    }

    @Test
    public void withGivenUserDto_shouldReturnUserDao() {
        UserDao actualUser = userMapper.toUserDao(new UserDto("Dummy", "100"));

        assertThat(actualUser.getName()).isEqualTo("Dummy");
    }

    @Test
    public void withGivenUserDao_shouldReturnUserDto() {
        UserDto actualUser = userMapper.toUserDto(new UserDao("Dummy", "100"));

        assertThat(actualUser.getName()).isEqualTo("Dummy");
    }

    @Test
    public void withGivenUserDaoList_shouldReturnUserDtoList() {
        List<UserDao> expectedList = Arrays.asList(new UserDao("Dummy", "100"));

        List<UserDto> actualList = userMapper.toUserDtoList(expectedList);

        //assertThat(actualList.get(0).getName()).isEqualTo("Dummy");
        assertThat(actualList.size()).isEqualTo(1);
    }
}