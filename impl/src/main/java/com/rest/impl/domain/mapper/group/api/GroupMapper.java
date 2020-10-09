package com.rest.impl.domain.mapper.group.api;

import com.rest.api.dto.GroupDto;
import com.rest.impl.dao.group.GroupDao;

import java.util.List;

public interface GroupMapper {
    GroupDto toGroupDto(GroupDao groupDao);

    GroupDao toGroupDao(GroupDto groupDto);

    List<GroupDto> toGroupDtoList(List<GroupDao> groupDaoList);
}
