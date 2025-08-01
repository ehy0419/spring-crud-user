package com.springcruduser.controller;

import com.springcruduser.dto.UserRequest;
import com.springcruduser.dto.UserResponse;
import com.springcruduser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public UserResponse createUser(
            @RequestBody UserRequest userRequest
    )
}
