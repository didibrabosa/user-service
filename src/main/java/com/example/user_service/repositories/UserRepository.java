package com.example.user_service.repositories;

import com.example.user_service.models.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUserId(String userId);

    Optional<User> findByEmail(String userId);

    void deleteByUserId(String userId);
}
