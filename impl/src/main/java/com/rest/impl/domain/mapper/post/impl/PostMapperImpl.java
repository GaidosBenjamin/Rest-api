package com.rest.impl.domain.mapper.post.impl;

import com.rest.api.dto.PostDto;
import com.rest.impl.dao.post.PostDao;
import com.rest.impl.domain.mapper.post.api.PostMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDto toPostDto(PostDao postDao) {

        return new PostDto(postDao.getDescription());
    }

    @Override
    public PostDao toPostDao(PostDto postDto) {
        PostDao post = new PostDao();
        post.setDescription(postDto.getDescription());
        return post;
    }

    @Override
    public List<PostDto> toPostDtoList(List<PostDao> postDaoList) {
        return postDaoList.stream()
                .map(post -> new PostDto(post.getDescription()))
                .collect(Collectors.toList());
    }
}
