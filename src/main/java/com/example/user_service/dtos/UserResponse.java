package com.example.user_service.dtos;

public record UserResponse(
    String userId,
    String email,
    String fullName,
    String phone
) {}