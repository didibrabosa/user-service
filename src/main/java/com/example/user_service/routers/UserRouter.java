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

    @PostMapping
    public ResponseEntity<UserResponse> createUser(
        @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUser(
        @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(userService.getUser(request.userId()));
    }

    @PatchMapping
    public ResponseEntity<UserResponse> updateUser(
        @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(userService.updateUser(request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(
        @RequestBody UserRequest request
    ) {
        userService.deleteUser(request.userId());
        return ResponseEntity.noContent().build();
    }
}