package com.rest.impl.domain.impl;

import com.rest.api.dto.PostDto;
import com.rest.api.dto.UserDto;
import com.rest.impl.dao.address.AddressDao;
import com.rest.impl.dao.address.repository.AddressRepository;
import com.rest.impl.dao.group.GroupDao;
import com.rest.impl.dao.post.PostDao;
import com.rest.impl.dao.post.repository.PostRepository;
import com.rest.impl.dao.UserDao;
import com.rest.impl.dao.repository.UserRepository;
import com.rest.impl.domain.api.UserService;
import com.rest.impl.exceptions.AddressNotFoundException;
import com.rest.impl.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AddressRepository addressRepository;


    @Override
    public List<UserDao> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDao saveUser(UserDao user) {
        return userRepository.save(user);
    }

    @Override
    public UserDao findById(Integer id) throws UserNotFoundException {
        UserDao user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }

    @Override
    public UserDao updateUser(Integer id, UserDao user) {
        userRepository.findById(id)
                .map(newUser -> {
                    newUser.setName(user.getName());
                    newUser.setAge(user.getAge());
                    return userRepository.save(newUser);
                }).orElse(userRepository.save(user));
        return user;
    }

    @Override
    public void deleteUser(Integer id) {
        UserDao user = findById(id);
        userRepository.delete(user);
    }

    @Override
    public PostDao savePost(Integer id, PostDao post) {
        UserDao user = findById(id);
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    public List<PostDao> getPosts(Integer id) {
        UserDao user = findById(id);

        return user.getPosts();
    }

    @Override
    public AddressDao getAddress(Integer id) throws AddressNotFoundException {
        UserDao user = findById(id);
        if(user.getAddress() == null)
            throw new AddressNotFoundException(id);
        return user.getAddress();
    }

    @Override
    public AddressDao saveAddress(Integer id, AddressDao address) {
        UserDao user = findById(id);
        user.setAddress(address);
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public List<GroupDao> getGroupsForUser(Integer id) {
        UserDao user = findById(id);
        return user.getGroups();
    }

    @Override
    public GroupDao saveGroup(Integer id, GroupDao groupDao) {
        UserDao user = findById(id);
        if(user.getGroups() == null) {
            user.setGroups(Arrays.asList(groupDao));
            userRepository.save(user);
        }
        else {
            user.addGroup(groupDao);
            userRepository.save(user);
        }
        return groupDao;
    }

}
