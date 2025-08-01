package com.springcruduser.controller;

import com.springcruduser.dto.UserRequest;
import com.springcruduser.dto.UserResponse;
import com.springcruduser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public UserResponse createUser(
            @RequestBody UserRequest userRequest
    ) {
        return userService.save(userRequest);
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return userService.findUsers();
    }

    @GetMapping("user/{userId}")
    public UserResponse getUser(
            @PathVariable Long userId) {
        return userService.finduser(userId);
    }

    @PutMapping("/user/{userId}")
    public UserResponse updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequest userRequest
    ) {
        return userService.update(userId, userRequest);
    }

    @DeleteMapping("/user/{uersId}")
    public void deleteUser(
            @PathVariable Long userId
    ) {
        userService.deleteUser(userId);
    }
}
