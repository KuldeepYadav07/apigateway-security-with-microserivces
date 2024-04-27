package com.userservice.userService.repository;

import com.userservice.userService.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<Users ,String> {
    Optional<Users> findByName(String name);
}
