package com.rest.impl.domain.impl;

import com.rest.impl.dao.UserDao;
import com.rest.impl.dao.address.AddressDao;
import com.rest.impl.dao.address.repository.AddressRepository;
import com.rest.impl.dao.group.GroupDao;
import com.rest.impl.dao.post.PostDao;
import com.rest.impl.dao.post.repository.PostRepository;
import com.rest.impl.dao.repository.UserRepository;
import com.rest.impl.exceptions.AddressNotFoundException;
import com.rest.impl.exceptions.UserNotFoundException;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void withGivenUserDaoList_shouldReturnUserDaoList() {
        UserDao user = new UserDao("Dummy", "100");
        List<UserDao> expectedUsers = new ArrayList<>();
        expectedUsers.add(user);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<UserDao> actualUsers = userService.getAll();

        assertThat(actualUsers).isEqualTo(expectedUsers);
        assertThat(actualUsers.size()).isEqualTo(1);
        assertThat(actualUsers.stream()
            .filter(u -> "Dummy".equals(u.getName()))
            .findAny()).isEqualTo(Optional.of(user));
    }

    @Test
    public void withGivenUserDao_shouldReturnUserDao() {
        UserDao expectedUser = new UserDao("Dummy", "100");
        when(userRepository.save(any())).thenReturn(expectedUser);

        UserDao actualUser = userService.saveUser(expectedUser);

        assertThat(expectedUser).isEqualTo(actualUser);
        assertThat(actualUser.getName()).isEqualTo("Dummy");
    }

    @Test(expected = UserNotFoundException.class)
    public void withGivenId_shouldReturnUserNotFoundException() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        userService.findById(any());
    }

    @Test
    public void withGivenId_shouldReturnUserDao() {
        UserDao expectedUser = new UserDao("Dummy", "100");
        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(expectedUser));

        UserDao actualUser = userService.findById(any());

        assertThat(expectedUser).isEqualTo(actualUser);
        assertThat(actualUser.getName()).isEqualTo("Dummy");
    }

    @Test
    public void withGivenIdAndUserDao_ShouldSaveAndReturnUserDao() {
        UserDao expectedUser = new UserDao("Dummy", "100");
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(expectedUser);

        UserDao actualUser = userService.updateUser(any(), expectedUser);

        assertThat(expectedUser).isEqualTo(actualUser);
        assertThat(actualUser.getName()).isEqualTo("Dummy");
    }

    @Test
    public void withGivenIdAndUserDao_shouldUpdateAndReturnUserDao() {
        UserDao expectedUser = new UserDao("Dummy", "100");
        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(new UserDao()));
        when(userRepository.save(any())).thenReturn(expectedUser);

        UserDao actualUser = userService.updateUser(any(), expectedUser);

        assertThat(expectedUser).isEqualTo(actualUser);
        assertThat(actualUser.getName()).isEqualTo("Dummy");
    }

    @Test
    public void withGivenId_shouldDelete() {
        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(new UserDao()));

        userService.deleteUser(any());

        verify(userRepository, times(1)).delete(any());
    }

    @Test
    public void withGivenIdAndPostDao_shouldSaveAndReturnPostDao() {
        UserDao user = new UserDao("Dummy", "100");
        PostDao expectedPost = new PostDao("Text");
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(postRepository.save(any())).thenReturn(expectedPost);

        PostDao actualPost = userService.savePost(any(), expectedPost);

        assertThat(actualPost).isEqualTo(expectedPost);
        assertThat(actualPost.getUser().getName()).isEqualTo("Dummy");
        assertThat(actualPost.getDescription()).isEqualTo("Text");
    }

    @Test
    public void withGivenId_shouldReturnPostDaoList() {
        PostDao post = new PostDao("Text");
        List<PostDao> expectedList = Arrays.asList(post);
        UserDao user = new UserDao("Dummy", "100");
        user.setPosts(expectedList);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        List<PostDao> actualList = userService.getPosts(any());

        assertThat(expectedList).isEqualTo(actualList);
        assertThat(actualList.stream()
            .filter(p -> "Text".equals(p.getDescription()))
            .findAny()).isEqualTo(Optional.of(post));
    }

    @Test(expected = AddressNotFoundException.class)
    public void withGivenUserId_shouldThrowAddressNotFoundException() {
        when(userRepository.findById(any())).thenReturn(Optional.of(new UserDao()));

        userService.getAddress(any());
    }

    @Test
    public void withGivenId_shouldReturnAddressDao() {
        UserDao user = new UserDao("Dummy", "200");
        AddressDao expectedAddress = new AddressDao("Text");
        user.setAddress(expectedAddress);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        AddressDao actualAddress = userService.getAddress(any());

        assertThat(expectedAddress).isEqualTo(actualAddress);
        assertThat(actualAddress.getAddress()).isEqualTo("Text");
    }

    @Test
    public void withGivenIdAndAddressDao_shouldSaveAndReturnAddress() {
        AddressDao expectedAddress = new AddressDao("Text");
        when(userRepository.findById(any())).thenReturn(Optional.of(new UserDao("Dummy", "100")));
        when(addressRepository.save(any())).thenReturn(expectedAddress);

        AddressDao actualAddress = userService.saveAddress(any(), new AddressDao());

        assertThat(actualAddress).isEqualTo(expectedAddress);
        assertThat(actualAddress.getAddress()).isEqualTo("Text");
    }

    @Test
    public void withGivenId_shouldReturnGroupsForUser() {
        GroupDao group = new GroupDao("Name");
        List<GroupDao> expected = Arrays.asList(group);
        UserDao user = new UserDao("Dummy", "100");
        user.setGroups(expected);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        List<GroupDao> actual = userService.getGroupsForUser(any());

        assertThat(actual).isEqualTo(expected);
        assertThat(actual.size()).isEqualTo(1);
        assertThat(actual.stream()
            .filter(g -> "Name".equals(g.getName()))
            .findAny().get().getName()).isEqualTo("Name");
    }

    @Test
    public void withGivenIdAndGroupDao_shouldSaveAndReturnGroupDao() {
        GroupDao expected = new GroupDao("Name");
        when(userRepository.findById(any())).thenReturn(Optional.of(new UserDao()));
        when(userRepository.save(any())).thenReturn(new UserDao());

        GroupDao actual = userService.saveGroup(any(), expected);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void withGivenIdAndGroupDao_shouldUpdateAndReturnGroupDao() {
        UserDao user = new UserDao("Dummy", "100");
        GroupDao expected = new GroupDao("Name");
        List<GroupDao> list = new ArrayList<>();
        list.add(new GroupDao());
        user.setGroups(list);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(new UserDao());

        GroupDao actual = userService.saveGroup(any(), expected);

        assertThat(actual).isEqualTo(expected);
    }


}