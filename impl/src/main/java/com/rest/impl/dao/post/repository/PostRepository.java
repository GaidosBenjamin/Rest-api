package com.rest.impl.dao.post.repository;

import com.rest.impl.dao.post.PostDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostDao, Integer> {
    Optional<PostDao> findByDescription(String description);
}
