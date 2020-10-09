package com.rest.impl.dao.address.repository;

import com.rest.impl.dao.address.AddressDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AddressRepository extends JpaRepository<AddressDao, Integer> {
}
