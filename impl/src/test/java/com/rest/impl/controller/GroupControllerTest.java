package com.rest.impl.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.dto.GroupDto;
import com.rest.api.dto.UserDto;
import com.rest.app.AppApplication;
import com.rest.impl.dao.UserDao;
import com.rest.impl.dao.group.GroupDao;
import com.rest.impl.domain.group.impl.GroupServiceImpl;
import com.rest.impl.domain.mapper.group.impl.GroupMapperImpl;
import com.rest.impl.domain.mapper.user.impl.UserMapperImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={AppApplication.class})
@AutoConfigureMockMvc
public class GroupControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GroupMapperImpl groupMapper;

    @MockBean
    private UserMapperImpl userMapper;

    @MockBean
    private GroupServiceImpl groupService;

    @Test
    public void with_shouldReturnGroupDtoList() throws Exception {
        List<GroupDto> expected = Arrays.asList(new GroupDto("Nerds"));

        when(groupService.getAll()).thenReturn(new ArrayList<>());
        when(groupMapper.toGroupDtoList(any())).thenReturn(expected);

        this.mockMvc.perform(get("/groups"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Nerds")));
    }

    @Test
    public void withGivenGroupDto_shouldReturnGroupDto() throws Exception {

        when(groupMapper.toGroupDao(any())).thenReturn(new GroupDao("Nerds"));
        when(groupService.saveGroup(any())).thenReturn(new GroupDao());

        this.mockMvc.perform(post("/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new GroupDto("Nerds"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Nerds")));
    }

    @Test
    public void withGivenId_shouldReturnUserDtoList() throws Exception {
        List<UserDto> list = Arrays.asList(new UserDto("Goku", "1000"));

        when(groupService.getUsersForGroup(any())).thenReturn(new ArrayList<>());
        when(userMapper.toUserDtoList(any())).thenReturn(list);

        this.mockMvc.perform(get("/groups/1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Goku")))
                .andExpect(jsonPath("$[0].age", is("1000")));
    }

    @Test
    public void withGivenIdAndUserDto_shouldReturnUserDto() throws Exception{

        when(userMapper.toUserDao(any())).thenReturn(new UserDao("Dummy", "100"));
        when(groupService.saveOrUpdateUser(any(), any())).thenReturn(new UserDao());

        this.mockMvc.perform(post("/groups/1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserDto("Dummy", "100"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Dummy")))
                .andExpect(jsonPath("$.age", is("100")));
    }

}