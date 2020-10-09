package com.rest.impl.domain.mapper.post.api;

import com.rest.api.dto.PostDto;
import com.rest.impl.dao.post.PostDao;

import java.util.List;

public interface PostMapper {
    PostDto toPostDto(PostDao postDao);

    PostDao toPostDao(PostDto postDto);

    List<PostDto> toPostDtoList(List<PostDao> postDaoList);
}
