package com.rest.impl.dao.repository;

import com.rest.impl.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDao, Integer> {
    Optional<UserDao> findByName(String name);
}
