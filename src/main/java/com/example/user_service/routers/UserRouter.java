package com.example.user_service.routers;

import com.example.user_service.dtos.UserRequest;
import com.example.user_service.dtos.UserResponse;
import com.example.user_service.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRouter {

    private final UserService userService;

    public UserRouter(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserResponse> createUser(
        @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(
        @PathVariable String userId
    ) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
        @PathVariable String userId,
        @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
        @PathVariable String userId
    ) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
