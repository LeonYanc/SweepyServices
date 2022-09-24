package com.sweepy.repository;


import com.sweepy.database.user;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userRepository extends CrudRepository<user, Long> {
    List<user> findByUsername(String username);
}