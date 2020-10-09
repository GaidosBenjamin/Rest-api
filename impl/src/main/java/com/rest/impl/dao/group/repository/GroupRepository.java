package com.rest.impl.dao.group.repository;

import com.rest.impl.dao.group.GroupDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupDao, Integer> {
}
