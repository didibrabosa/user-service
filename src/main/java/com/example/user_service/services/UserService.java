package com.example.user_service.services;

import com.example.user_service.models.User;
import com.example.user_service.dtos.UserRequest;
import com.example.user_service.dtos.UserResponse;
import com.example.user_service.repositories.UserRepository;
import com.example.user_service.exceptions.UserAlreadyExistsException;
import com.example.user_service.exceptions.UserNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest request) {
        if(userRepository.findByEmail(request.email()).isPresent()) {
            throw new UserAlreadyExistsException("User with email '"+ request.email() +"' already exists.");
        }
        
        User user = new User();
        user.setUserId(request.userId());
        user.setEmail(request.email());
        User savedUser = userRepository.save(user);
        
        return new UserResponse(
        savedUser.getUserId(),
        savedUser.getEmail(),
        savedUser.getFullName(),
        savedUser.getPhone()
    );
    }

    public UserResponse getUser(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return new UserResponse(
            user.getUserId(), 
            user.getEmail(),
            user.getFullName(),
            user.getPhone());
    }

    public UserResponse updateUser(String userId, UserRequest request) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        if (request.email() != null) user.setEmail(request.email());
        if (request.fullName() != null) user.setFullName(request.fullName());
        if (request.phone() != null) user.setPhone(request.phone());

        User updatedUser = userRepository.save(user);
        return new UserResponse(
            updatedUser.getUserId(),
            updatedUser.getEmail(),
            updatedUser.getFullName(),
            updatedUser.getPhone()
        );
    }

    public void deleteUser(String userId) {
        userRepository.findByUserId(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        userRepository.deleteByUserId(userId);
    }
}
