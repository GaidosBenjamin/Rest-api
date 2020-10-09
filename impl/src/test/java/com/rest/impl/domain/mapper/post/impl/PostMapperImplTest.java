package com.rest.impl.domain.mapper.post.impl;

import com.rest.api.dto.PostDto;
import com.rest.impl.dao.post.PostDao;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PostMapperImplTest {

    private PostMapperImpl postMapper;

    @Before
    public void setUp() {
        postMapper = new PostMapperImpl();
    }

    @Test
    public void withGivenPostDto_shouldReturnPostDao() {
        PostDao actualPost = postMapper.toPostDao(new PostDto("Text"));

        assertThat(actualPost.getDescription()).isEqualTo("Text");
    }

    @Test
    public void withGivenPostDao_shouldReturnPostDto() {
        PostDto actualPost = postMapper.toPostDto(new PostDao("Text"));

        assertThat(actualPost.getDescription()).isEqualTo("Text");
    }

    @Test
    public void withGivenPostDaoList_shouldReturnPostDtoList() {
        List<PostDao> expectedList = Arrays.asList(new PostDao("Text"));

        List<PostDto> actualList = postMapper.toPostDtoList(expectedList);

        //assertThat(actualList.get(0).getDescription()).isEqualTo("Text");
        assertThat(actualList.size()).isEqualTo(1);
    }
}