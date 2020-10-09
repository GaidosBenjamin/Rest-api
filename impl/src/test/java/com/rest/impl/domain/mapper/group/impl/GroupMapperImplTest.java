package com.rest.impl.domain.mapper.group.impl;

import com.rest.api.dto.GroupDto;
import com.rest.impl.dao.group.GroupDao;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GroupMapperImplTest {

    private GroupMapperImpl groupMapper;

    @Before
    public void setUp() {
        groupMapper = new GroupMapperImpl();
    }

    @Test
    public void whenGivenGroupDto_shouldReturnGroupDao() {
        GroupDao actual = groupMapper.toGroupDao(new GroupDto("Name"));

        assertThat(actual.getName()).isEqualTo("Name");
    }

    @Test
    public void whenGivenGroupDao_shouldReturnGroupDto() {
        GroupDto actual = groupMapper.toGroupDto(new GroupDao("Name"));

        assertThat(actual.getName()).isEqualTo("Name");
    }

    @Test
    public void whenGivenGroupDaoList_shouldReturnGroupDtoList() {
        List<GroupDao> list = new ArrayList<>();
        list.add(new GroupDao("Name"));

        List<GroupDto> actual = groupMapper.toGroupDtoList(list);

        assertThat(actual.size()).isEqualTo(1);
        assertThat(actual.stream().filter(g -> "Name".equals(g.getName())).findAny().get().getName())
                .isEqualTo("Name");
    }

}