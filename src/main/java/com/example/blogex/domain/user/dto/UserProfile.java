package com.example.blogex.domain.user.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserProfile {

    /*
    userProfile 로 볼 정보
     */

    private String username;
    private String email;
    private Long id;
    private String description;
    private String profileImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int followersCount;
    private int followingCount;
    private int postCount;
    private int commentCount;

    @Builder
    public UserProfile(String username,
                       String email,
                       Long id,
                       String description,
                       String profileImageUrl,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt,
                       int followersCount,
                       int followingCount,
                       int postCount,
                       int commentCount) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.description = description;
        this.profileImageUrl = profileImageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.postCount = postCount;
        this.commentCount = commentCount;
    }


}
