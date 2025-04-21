package com.example.blogex.domain.user.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserInfo {

    /*
    *  유저의 간단한 정보만 읽어오는 용도
    * 게시판에서 작성자 확인하거나 좋아요 목록 등 확인용도
    * */
    private String username;
    private String email;
    private String description;
    private String profileImageUrl;
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Builder
    public UserInfo(String username,
                    String email,
                    String description,
                    String profileImageUrl,
                    Long id,
                    LocalDateTime createdAt,
                    LocalDateTime updatedAt) {
        this.username = username;
        this.email = email;
        this.description = description;
        this.profileImageUrl = profileImageUrl;
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
