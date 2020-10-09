package com.rest.impl.domain.group.impl;

import com.rest.impl.dao.UserDao;
import com.rest.impl.dao.group.GroupDao;
import com.rest.impl.dao.group.repository.GroupRepository;
import com.rest.impl.dao.repository.UserRepository;
import com.rest.impl.domain.group.api.GroupService;
import com.rest.impl.exceptions.GroupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public GroupDao findById(Integer id) throws GroupNotFoundException {
        GroupDao group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
        return group;
    }

    @Override
    public List<GroupDao> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public GroupDao saveGroup(GroupDao groupDao) {
        return groupRepository.save(groupDao);
    }

    @Override
    public List<UserDao> getUsersForGroup(Integer id) {
        GroupDao group = findById(id);
        return group.getUsers();
    }

    @Override
    public UserDao saveOrUpdateUser(Integer id, UserDao user) {
        GroupDao group = findById(id);
        if(group.getUsers() == null)
            group.setUsers(Arrays.asList(user));
        else
            group.addUser(user);
        groupRepository.save(group);
        return user;
    }
}
