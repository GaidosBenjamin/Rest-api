package com.rest.impl.domain.group.api;

import com.rest.impl.dao.UserDao;
import com.rest.impl.dao.group.GroupDao;
import com.rest.impl.exceptions.GroupNotFoundException;

import java.util.List;

public interface GroupService {
    GroupDao findById(Integer id) throws GroupNotFoundException;

    List<GroupDao> getAll();

    GroupDao saveGroup(GroupDao groupDao);

    List<UserDao> getUsersForGroup(Integer id);

    UserDao saveOrUpdateUser(Integer id, UserDao user);
}
