package com.example.user_service.dtos;

public record UserRequest(
    String userId,
    String email
) {}