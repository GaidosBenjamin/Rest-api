package com.rest.impl.domain.api;

import com.rest.api.dto.GroupDto;
import com.rest.api.dto.PostDto;
import com.rest.api.dto.UserDto;
import com.rest.impl.dao.UserDao;
import com.rest.impl.dao.address.AddressDao;
import com.rest.impl.dao.group.GroupDao;
import com.rest.impl.dao.post.PostDao;
import com.rest.impl.exceptions.AddressNotFoundException;
import com.rest.impl.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

    List<UserDao> getAll();

    UserDao saveUser(UserDao user);

    UserDao findById(Integer id) throws UserNotFoundException;

    UserDao updateUser(Integer id, UserDao user);

    void deleteUser(Integer id);

    PostDao savePost(Integer id, PostDao post);

    List<PostDao> getPosts(Integer id);

    AddressDao getAddress(Integer id) throws AddressNotFoundException;

    AddressDao saveAddress(Integer id, AddressDao address);

    List<GroupDao> getGroupsForUser(Integer id);

    GroupDao saveGroup(Integer id, GroupDao groupDao);
}
