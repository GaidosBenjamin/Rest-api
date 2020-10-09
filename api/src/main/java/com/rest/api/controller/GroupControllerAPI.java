package com.rest.api.controller;

import com.rest.api.dto.GroupDto;
import com.rest.api.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/groups")
public interface GroupControllerAPI {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<GroupDto>> getGroups();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<GroupDto> saveGroup(@RequestBody GroupDto groupDto);

    @GetMapping("/{id}/users")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<UserDto>> getUsersForGroup(@PathVariable Integer id);

    @PostMapping("/{id}/users")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserDto> saveUser(@PathVariable Integer id, @RequestBody UserDto userDto);
}
