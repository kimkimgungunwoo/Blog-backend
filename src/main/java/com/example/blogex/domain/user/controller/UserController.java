package com.example.blogex.domain.user.controller;

import com.example.blogex.domain.user.dto.UserCreateRequest;
import com.example.blogex.domain.user.dto.UserCreateResponse;
import com.example.blogex.domain.user.dto.UserProfile;
import com.example.blogex.domain.user.entitiy.User;
import com.example.blogex.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping()
    public UserCreateResponse createUser(@RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{userId}")
    public UserProfile getUser(@PathVariable Long userId) {
        return userService.getUserProfile(userId);
    }





}
