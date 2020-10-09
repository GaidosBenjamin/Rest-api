package com.rest.impl.controller;

import com.rest.api.dto.AddressDto;
import com.rest.api.dto.GroupDto;
import com.rest.api.dto.PostDto;
import com.rest.api.controller.UserControllerAPI;
import com.rest.api.dto.UserDto;
import com.rest.impl.dao.UserDao;
import com.rest.impl.dao.post.PostDao;
import com.rest.impl.domain.api.UserService;
import com.rest.impl.domain.mapper.adress.impl.AddressMapperImpl;
import com.rest.impl.domain.mapper.group.impl.GroupMapperImpl;
import com.rest.impl.domain.mapper.post.impl.PostMapperImpl;
import com.rest.impl.domain.mapper.user.impl.UserMapperImpl;
import com.rest.impl.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController implements UserControllerAPI {
    @Autowired
    private PostMapperImpl postMapper;

    @Autowired
    private UserMapperImpl userMapper;

    @Autowired
    private AddressMapperImpl addressMapper;

    @Autowired
    private GroupMapperImpl groupMapper;

    @Autowired
    private UserService service;

    @Override
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = userMapper.toUserDtoList(service.getAll());
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<UserDto> saveUser(UserDto user) {
        service.saveUser(userMapper.toUserDao(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Override
    public ResponseEntity<UserDto> getOne(Integer id) throws UserNotFoundException {
        UserDao user = service.findById(id);
        return ResponseEntity.ok(userMapper.toUserDto(user));
    }

    @Override
    public ResponseEntity<UserDto> saveOrUpdateUser(Integer id, UserDto user) throws UserNotFoundException {
        service.updateUser(id, userMapper.toUserDao(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Override
    public ResponseEntity deleteUser(Integer id) {
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<List<PostDto>> getPosts(Integer id) throws UserNotFoundException{
        List<PostDao> list = service.getPosts(id);

        return ResponseEntity.status(HttpStatus.OK).body(postMapper.toPostDtoList(list));
    }

    @Override
    public ResponseEntity<PostDto> savePost(Integer id, PostDto post) throws UserNotFoundException{
        PostDao newPost = postMapper.toPostDao(post);
        service.savePost(id, newPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @Override
    public ResponseEntity<AddressDto> getAddress(Integer id) {
        AddressDto address = addressMapper.toAddressDto(service.getAddress(id));
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @Override
    public ResponseEntity<AddressDto> saveAddress(Integer id, AddressDto address) {
        service.saveAddress(id, addressMapper.toAddressDao(address));
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @Override
    public ResponseEntity<List<GroupDto>> getGroupsForUser(Integer id) {
        return ResponseEntity.ok(groupMapper.toGroupDtoList(service.getGroupsForUser(id)));
    }

    @Override
    public ResponseEntity<GroupDto> saveGroup(Integer id, GroupDto groupDto) {
        service.saveGroup(id, groupMapper.toGroupDao(groupDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(groupDto);
    }
}
