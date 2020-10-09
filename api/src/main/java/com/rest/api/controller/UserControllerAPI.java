package com.rest.api.controller;

import com.rest.api.dto.AddressDto;
import com.rest.api.dto.GroupDto;
import com.rest.api.dto.PostDto;
import com.rest.api.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public interface UserControllerAPI {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<UserDto>> getAll();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserDto> saveUser(@RequestBody UserDto user);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserDto> getOne(@PathVariable Integer id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserDto> saveOrUpdateUser(@PathVariable Integer id, @RequestBody UserDto user);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ResponseEntity deleteUser(@PathVariable Integer id);

    @GetMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<PostDto>> getPosts(@PathVariable Integer id);

    @PostMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<PostDto> savePost(@PathVariable Integer id, @RequestBody PostDto post);

    @GetMapping("/{id}/address")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<AddressDto> getAddress(@PathVariable Integer id);

    @PostMapping("/{id}/address")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<AddressDto> saveAddress(@PathVariable Integer id, @RequestBody AddressDto address);

    @GetMapping("/{id}/groups")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<GroupDto>> getGroupsForUser(@PathVariable Integer id);

    @PostMapping("/{id}/groups")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<GroupDto> saveGroup(@PathVariable Integer id, @RequestBody GroupDto groupDto);

}
