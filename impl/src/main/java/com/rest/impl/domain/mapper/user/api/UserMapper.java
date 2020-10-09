package com.rest.impl.domain.mapper.user.api;

import com.rest.api.dto.UserDto;
import com.rest.impl.dao.UserDao;

import java.util.List;

public interface UserMapper {
    UserDto toUserDto(UserDao userDao);

    UserDao toUserDao(UserDto userDto);

    List<UserDto> toUserDtoList(List<UserDao> userDaoList);
}
