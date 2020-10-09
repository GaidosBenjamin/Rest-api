package com.rest.app;

import com.rest.api.dto.PostDto;
import com.rest.api.dto.UserDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate.postForEntity("/users", new UserDto("Dummy", "100"), UserDto.class);
        restTemplate.postForEntity("/users", new UserDto("Asap", "200"), UserDto.class);
    }


    @Test
    public void testSaveUser() {
        ResponseEntity<UserDto> responseEntity = restTemplate.postForEntity("/users", new UserDto("Benji", "300"), UserDto.class);
        UserDto userDto = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(userDto.getName()).isEqualTo("Benji");
    }

    @Test
    public void testSavePost() {
        ResponseEntity<PostDto> responseEntity = restTemplate.postForEntity("/users/1/posts", new PostDto("Text"), PostDto.class);
        PostDto postDto = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(postDto.getDescription()).isEqualTo("Text");
    }

    @Test
    public void testGetUser() {
        ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity("/users/1", UserDto.class);
        UserDto userDto = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userDto.getName()).isEqualTo("Dummy");
    }

    @Test
    public void testGetAllUsers() {
        ResponseEntity<UserDto[]> responseEntity = restTemplate.getForEntity("/users", UserDto[].class);
        UserDto[] userDtoList = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userDtoList.length).isEqualTo(2);
        assertThat(userDtoList[0].getName()).isEqualTo("Dummy");
    }

    //@Test
    //public void testDeleteUser()

    @Test
    public void testGetAllPostsForOneUser() {
        restTemplate.postForEntity("/users/1/posts", new PostDto("Text"), PostDto.class);
        ResponseEntity<PostDto[]> responseEntity = restTemplate.getForEntity("/users/1/posts", PostDto[].class);
        PostDto[] postDtoList = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(postDtoList.length).isEqualTo(1);
        assertThat(postDtoList[0].getDescription()).isEqualTo("Text");
    }
}
