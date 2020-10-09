package com.rest.impl.controller;

import com.rest.api.controller.GroupControllerAPI;
import com.rest.api.dto.GroupDto;
import com.rest.api.dto.UserDto;
import com.rest.impl.domain.group.api.GroupService;
import com.rest.impl.domain.group.impl.GroupServiceImpl;
import com.rest.impl.domain.mapper.group.impl.GroupMapperImpl;
import com.rest.impl.domain.mapper.user.impl.UserMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class GroupController implements GroupControllerAPI {
    @Autowired
    GroupServiceImpl groupService;

    @Autowired
    GroupMapperImpl groupMapper;

    @Autowired
    UserMapperImpl userMapper;

    @Override
    public ResponseEntity<List<GroupDto>> getGroups() {
        return ResponseEntity.ok(groupMapper.toGroupDtoList(groupService.getAll()));
    }

    @Override
    public ResponseEntity<GroupDto> saveGroup(GroupDto groupDto) {
        groupService.saveGroup(groupMapper.toGroupDao(groupDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(groupDto);
    }

    @Override
    public ResponseEntity<List<UserDto>> getUsersForGroup(Integer id) {
        return ResponseEntity.ok(userMapper.toUserDtoList(groupService.getUsersForGroup(id)));
    }

    @Override
    public ResponseEntity<UserDto> saveUser(Integer id, UserDto userDto) {
        groupService.saveOrUpdateUser(id, userMapper.toUserDao(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
}
