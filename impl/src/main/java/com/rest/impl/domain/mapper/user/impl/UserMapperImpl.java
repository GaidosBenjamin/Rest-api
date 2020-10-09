package com.rest.impl.domain.mapper.user.impl;

import com.rest.api.dto.UserDto;
import com.rest.impl.dao.UserDao;
import com.rest.impl.domain.mapper.user.api.UserMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toUserDto(UserDao userDao) {
        return new UserDto(userDao.getName(), userDao.getAge());
    }

    @Override
    public UserDao toUserDao(UserDto userDto) {
        UserDao user = new UserDao();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        return user;
    }

    @Override
    public List<UserDto> toUserDtoList(List<UserDao> userDaoList) {
        return userDaoList.stream()
                .map(user -> new UserDto(user.getName(), user.getAge()))
                .collect(Collectors.toList());
    }
}
