package com.example.user_service.routers;

import com.example.user_service.dtos.UserRequest;
import com.example.user_service.dtos.UserResponse;
import com.example.user_service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRouter {

    private static final Logger logger = LoggerFactory.getLogger(UserRouter.class);

    private final UserService userService;

    public UserRouter(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserResponse> createUser(
        @RequestBody UserRequest request
    ) {
        logger.info("Received request to create user with email: {}", request.email());
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(
        @PathVariable String userId
    ) {
        logger.info("Received request to get user with id: {}", userId);
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
        @PathVariable String userId,
        @RequestBody UserRequest request
    ) {
        logger.info("Received request to update user with id: {}", userId);
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
        @PathVariable String userId
    ) {
        logger.info("Received request to delete user with id: {}", userId);
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
