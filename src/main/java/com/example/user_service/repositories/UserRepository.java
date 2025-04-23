package com.example.user_service.repositories;

import com.example.user_service.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUserId(String userId);

    void deleteByUserId(String userId);
}
