package com.rest.impl.domain.mapper.group.impl;

import com.rest.api.dto.GroupDto;
import com.rest.impl.dao.group.GroupDao;
import com.rest.impl.domain.mapper.group.api.GroupMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapperImpl implements GroupMapper {
    @Override
    public GroupDto toGroupDto(GroupDao groupDao) {
        return new GroupDto(groupDao.getName());
    }

    @Override
    public GroupDao toGroupDao(GroupDto groupDto) {
        GroupDao groupDao = new GroupDao();
        groupDao.setName(groupDto.getName());
        return groupDao;
    }

    @Override
    public List<GroupDto> toGroupDtoList(List<GroupDao> groupDaoList) {
        return groupDaoList.stream()
                .map(group -> new GroupDto(group.getName()) )
                .collect(Collectors.toList());
    }
}
