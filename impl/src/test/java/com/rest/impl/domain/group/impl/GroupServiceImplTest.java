package com.rest.impl.domain.group.impl;

import com.rest.impl.dao.UserDao;
import com.rest.impl.dao.group.GroupDao;
import com.rest.impl.dao.group.repository.GroupRepository;
import com.rest.impl.exceptions.GroupNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GroupServiceImplTest {
    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupServiceImpl groupService;

    @Test(expected = GroupNotFoundException.class)
    public void withGivenId_shouldThrowGroupNotFoundException() {
        when(groupRepository.findById(any())).thenReturn(Optional.empty());

        groupService.findById(any());
    }

    @Test
    public void withGivenId_shouldReturnGroupDao()  {
        GroupDao expected = new GroupDao("Name");
        when(groupRepository.findById(any())).thenReturn(Optional.of(expected));

        GroupDao actual = groupService.findById(any());

        assertThat(expected).isEqualTo(actual);
        assertThat(actual.getName()).isEqualTo("Name");
    }

    @Test
    public void with_shouldReturnGroupDaoList() {
        GroupDao group = new GroupDao("Name");
        List<GroupDao> expected = Arrays.asList(group);
        when(groupRepository.findAll()).thenReturn(expected);

        List<GroupDao> actual = groupService.getAll();

        assertThat(actual.size()).isEqualTo(1);
        assertThat(actual.stream().filter(g -> "Name".equals(g.getName())).findAny())
                .isEqualTo(Optional.of(group));
    }

    @Test
    public void withGivenGroupDao_shouldSaveAndReturnGroupDao() {
        GroupDao expected = new GroupDao("Name");
        when(groupRepository.save(any())).thenReturn(expected);

        GroupDao actual = groupService.saveGroup(any());

        assertThat(expected).isEqualTo(actual);
        assertThat(actual.getName()).isEqualTo("Name");
    }

    @Test
    public void withGivenId_shouldReturnUserDaoList() {
        UserDao user = new UserDao("Dummy", "100");
        List<UserDao> expected = Arrays.asList(user);
        GroupDao group = new GroupDao("Name");
        group.setUsers(expected);
        when(groupRepository.findById(any())).thenReturn(Optional.of(group));

        List<UserDao> actual = groupService.getUsersForGroup(any());

        assertThat(expected).isEqualTo(actual);
        assertThat(actual.size()).isEqualTo(1);
        assertThat(actual.stream().filter(u -> "Dummy".equals(u.getName())).findAny())
                .isEqualTo(Optional.of(user));
    }

    @Test
    public void withGivenIdAndUserDao_shouldSaveAndReturnUserDao() {
        UserDao expected = new UserDao("Dummy", "100");
        GroupDao group = new GroupDao("Name");
        when(groupRepository.findById(any())).thenReturn(Optional.of(group));

        UserDao actual = groupService.saveOrUpdateUser(any(), expected);

        assertThat(expected).isEqualTo(actual);
        assertThat(actual.getName()).isEqualTo("Dummy");
        verify(groupRepository, times(1)).save(any());
    }

    @Test
    public void withGivenIdAndUserDao_shouldUpdateAndReturnUserDao() {
        UserDao expected = new UserDao("Dummy", "100");
        List<UserDao> list = new ArrayList<>();
        list.add(new UserDao());
        GroupDao group = new GroupDao("Name");
        group.setUsers(list);
        when(groupRepository.findById(any())).thenReturn(Optional.of(group));

        UserDao actual = groupService.saveOrUpdateUser(any(), expected);

        assertThat(expected).isEqualTo(actual);
        assertThat(actual.getName()).isEqualTo("Dummy");
        verify(groupRepository, times(1)).save(any());
    }

}