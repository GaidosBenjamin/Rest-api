package com.rest.impl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.dto.AddressDto;
import com.rest.api.dto.GroupDto;
import com.rest.api.dto.PostDto;
import com.rest.api.dto.UserDto;
import com.rest.app.AppApplication;
import com.rest.impl.dao.UserDao;
import com.rest.impl.dao.address.AddressDao;
import com.rest.impl.domain.impl.UserServiceImpl;
import com.rest.impl.domain.mapper.adress.impl.AddressMapperImpl;
import com.rest.impl.domain.mapper.group.impl.GroupMapperImpl;
import com.rest.impl.domain.mapper.post.impl.PostMapperImpl;
import com.rest.impl.domain.mapper.user.impl.UserMapperImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={AppApplication.class})
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GroupMapperImpl groupMapper;

    @MockBean
    private AddressMapperImpl addressMapper;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private UserMapperImpl userMapper;

    @MockBean
    private PostMapperImpl postMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenCallingGet_shouldReturnUserDtoList() throws Exception{
        UserDto user = new UserDto("Dummy", "100");
        List<UserDto> userDtoList = Arrays.asList(user);

        doReturn(new ArrayList<>()).when(userService).getAll();
        doReturn(userDtoList).when(userMapper).toUserDtoList(any());

        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Dummy")));
    }

    @Test
    public void whenGivenUserDtoAndCallingPost_shouldReturnUserDto() throws Exception {

        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserDto("Dummy", "100"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Dummy")))
                .andExpect(jsonPath("$.age", is("100")));
    }

    @Test
    public void whenGivenIdAndCallingGet_shouldReturnUserDto() throws Exception {

        when(userService.findById(1)).thenReturn(new UserDao("Dummy", "100"));
        when(userMapper.toUserDto(any())).thenReturn(new UserDto("Dummy", "100"));

        this.mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Dummy")));
    }

    @Test
    public void whenGivenIdAndPostDtoAndCallingPost_shouldReturnPostDto() throws Exception{

        this.mockMvc.perform(post("/users/1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new PostDto("Text"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description", is("Text")));
    }

    @Test
    public void whenGivenIdAndPostDtoAndCallingGet_shouldReturnPostDtoList() throws Exception {
        PostDto post = new PostDto("Text");
        List<PostDto> postDtoList = Arrays.asList(post);

        when(userService.getPosts(any())).thenReturn(new ArrayList<>());
        when(postMapper.toPostDtoList(any())).thenReturn(postDtoList);

        this.mockMvc.perform(get("/users/1/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description", is("Text")));
    }

    @Test
    public void whenGivenIdAndCallingDelete_shouldReturnIsAccepted() throws Exception {

        this.mockMvc.perform(delete("/users/1"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void whenGivenIdAndUserDtoAndCallingPut_shouldReturnUserDto() throws Exception {

        when(userService.updateUser(any(), any())).thenReturn(new UserDao());

        this.mockMvc.perform(put("/users/1").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserDto("Dummy", "100"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Dummy")));
    }

    @Test
    public void whenGivenId_shouldReturnUserAddressDto() throws Exception {
        AddressDto address = new AddressDto("Tokyo");
        when(addressMapper.toAddressDto(any())).thenReturn(address);

        this.mockMvc.perform(get("/users/1/address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address", is("Tokyo")));
    }

    @Test
    public void withGivenIdAndAddressDto_shouldReturnAddressDto() throws Exception {
        //when(addressMapper.toAddressDao(any())).thenReturn(new AddressDao("Kyoto"));
        //when(userService.saveAddress(any(), any())).thenReturn(new AddressDao());

        this.mockMvc.perform(post("/users/1/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new AddressDto("Kyoto"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.address", is("Kyoto")));

    }

    @Test
    public void withGivenId_shouldReturnGroupDtoList() throws Exception{
        List<GroupDto> expected = Arrays.asList(new GroupDto("Nerds"));

        when(groupMapper.toGroupDtoList(any())).thenReturn(expected);
        when(userService.getGroupsForUser(any())).thenReturn(new ArrayList<>());

        this.mockMvc.perform(get("/users/1/groups"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Nerds")));
    }

    @Test
    public void withGivenIdAndGroupDto_shouldReturnGroupDto() throws Exception {

        this.mockMvc.perform(post("/users/1/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new GroupDto("Geeks"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Geeks")));
    }


}