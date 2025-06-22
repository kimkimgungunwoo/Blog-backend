package com.example.blogex.domain.user.controller;

import com.example.blogex.domain.commentlike.dto.LikedCommentByUserResponse;
import com.example.blogex.domain.commentlike.service.CommentLikeService;
import com.example.blogex.domain.postlike.dto.LikedPostByUserResponse;
import com.example.blogex.domain.postlike.dto.LikedUserResponse;
import com.example.blogex.domain.postlike.entitiy.PostLike;
import com.example.blogex.domain.postlike.service.PostLIkeService;
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
    private final PostLIkeService postLIkeService;
    private final CommentLikeService commentLikeService;

    @PostMapping()
    public UserCreateResponse createUser(@RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{userId}")
    public UserProfile getUser(@PathVariable Long userId) {
        return userService.getUserProfile(userId);
    }


    @GetMapping("/likes/post/{userId}")
    public List<LikedPostByUserResponse> getPostLikes(@PathVariable Long userId) {
        return postLIkeService.getPostsLikedByUser(userId);
    }

    @GetMapping("/likes/comment/{userId}")
    public List<LikedCommentByUserResponse> getCommentLikes(@PathVariable Long userId) {
        return commentLikeService.getCommentsLikedByUser(userId);
    }





}
