package com.example.user_service.services;

import com.example.user_service.models.User;
import com.example.user_service.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User creatUser(String userId, String email) {
        User user = new User();
        user.setUserId(userId);
        user.setEmail(email);
        return userRepository.save(user);
    }

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    public User updateUser(String userId, String name, String phone) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (name != null) user.setFullName(name);
        if (phone != null) user.setPhone(phone);

        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteByUserId(userId);
    }
}
