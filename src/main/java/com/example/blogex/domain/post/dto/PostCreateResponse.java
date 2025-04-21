package com.example.blogex.domain.post.dto;


import com.example.blogex.domain.user.dto.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostCreateResponse {
    private Long id;
    private String title;
    private UserInfo userInfo;
    private LocalDateTime createdAt;

    @Builder
    public PostCreateResponse(Long id,
                              String title,
                              UserInfo userInfo,
                              LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.userInfo = userInfo;
        this.createdAt = createdAt;

    }

}
