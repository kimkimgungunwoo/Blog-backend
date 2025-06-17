package com.example.blogex.domain.user.service;


import com.example.blogex.domain.comment.repository.CommentRepository;
import com.example.blogex.domain.follow.repository.FollowRepository;
import com.example.blogex.domain.post.repository.PostRepository;
import com.example.blogex.domain.user.dto.*;
import com.example.blogex.domain.user.entitiy.User;
import com.example.blogex.domain.user.mapper.UserMapper;
import com.example.blogex.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final UserMapper userMapper;


    public UserCreateResponse createUser(UserCreateRequest userCreateRequest) {
        User user=userMapper.toEntity(userCreateRequest);

        return userMapper.toUserCreateResponse(userRepository.save(user));
    }

    public UserProfile getUserProfile(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User not found"));

        return UserProfile.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .id(user.getId())
                .description(user.getDescription())
                .profileImageUrl(user.getProfileImage())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .followersCount(followRepository.countByFollowingUserId(userId))
                .followingCount(followRepository.countByFollowerId(userId))
                .postCount(user.getPosts().size())
                .commentCount(user.getComments().size()).
                build();



    }

    public List<User> findPopularUser(){
        return userRepository.findPopularByFollower();
    }

    public UserInfo updateUsername(Long userId, String newUsername) {
        User user=userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User not found"));
        user.setUsername(newUsername);
        return userMapper.toUserInfo(userRepository.save(user));
    }

    public UserInfo updateEmail(Long userId, String newEmail) {
        User user=userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User not found"));
        user.setEmail(newEmail);
        return userMapper.toUserInfo(userRepository.save(user));
    }

    public UserInfo updatePassword(Long userId, String newPassword) {
        User user=userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User not found"));
        user.setPassword(newPassword);
        return userMapper.toUserInfo(userRepository.save(user));
    }

    public UserInfo updateProfileImage(Long userId, String newProfileImage) {
        User user=userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User not found"));
        user.setProfileImage(newProfileImage);
        return userMapper.toUserInfo(userRepository.save(user));
    }

    public UserInfo updateDescription(Long userId, String newDescription) {
        User user=userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User not found"));
        user.setDescription(newDescription);
        return userMapper.toUserInfo(userRepository.save(user));
    }





}
