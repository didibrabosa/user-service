package com.example.user_service.repositories;

import com.example.user_service.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRespository extends MongoRepository<User, String> {
        
    User findByUserId(String userId);

    User findByEmail(String email);

    void deleteByUserId(String userId);
}
