package com.example.blogex.domain.follow.service;


import com.example.blogex.domain.follow.dto.FollowInfo;
import com.example.blogex.domain.follow.entitiy.Follow;
import com.example.blogex.domain.follow.repository.FollowRepository;
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
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    //팔로우 기능
    public void follow(Long followerId, Long followingUserId ) {
        User followingUser=userRepository.findById(followingUserId)
                .orElseThrow(()->new EntityNotFoundException("User not found"));

        User follower=userRepository.findById(followerId)
                .orElseThrow(()->new EntityNotFoundException("Follower not found"));

        if(followRepository.existsByFollowerIdAndFollowingUserId(followerId,followingUserId)){
            followRepository.deleteByFollowerIdAndFollowingUserId(followerId,followingUserId);
        }
        else{
            Follow follow=new Follow(follower,followingUser);
            followRepository.save(follow);
        }


    }

    public boolean isFollowing(Long followerId, Long followingUserId) {
        User followingUser=userRepository.findById(followingUserId)
                .orElseThrow(()->new EntityNotFoundException("User not found"));

        User follower=userRepository.findById(followerId)
                .orElseThrow(()->new EntityNotFoundException("Follower not found"));

        return followRepository.existsByFollowerIdAndFollowingUserId(followerId,followingUserId);
    }

    public boolean isMutual(Long followerId, Long followingUserId) {
        return isFollowing(followerId,followingUserId) && isFollowing(followingUserId,followerId);
    }

    public List<FollowInfo> getFollowers(Long userId){
        User user=userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("User not found"));

         List<Follow> followList= followRepository.findByFollowingUserIdOrderByCreatedAtDesc(userId);
         return followList.stream()
                 .map(f->{
                     User follower=f.getFollower();
                     return FollowInfo.builder()
                             .userSimpleInfo(userMapper.toUserSimpleInfo(follower))
                             .createdAt(f.getCreatedAt())
                             .isMutual(isMutual(follower.getId(),userId)).build();
                 }).toList();
    }

    public List<FollowInfo>getFollows(Long userId){
        User user=userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("User not found"));

        List<Follow> followList=followRepository.findByFollowerIdOrderByCreatedAtDesc(userId);

        return followList.stream().map(
                f->{
                    User followee=f.getFollowingUser();
                    return  FollowInfo.builder()
                            .userSimpleInfo(userMapper.toUserSimpleInfo(followee))
                            .createdAt(f.getCreatedAt())
                            .isMutual(isMutual(followee.getId(),userId)).build();


                }
        ).toList();
    }






}
