package com.example.blogex.domain.postlike.dto;


import com.example.blogex.domain.post.dto.PostInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//유저가 좋아요 누른 포스트 정보 나중에 리스트에 담아서 추출
@Getter
@NoArgsConstructor
public class UserLikedPost {
    private PostInfo postInfo;
    private LocalDateTime createdAt;

    @Builder
    public UserLikedPost(PostInfo postInfo,
                         LocalDateTime createdAt) {
        this.postInfo = postInfo;
        this.createdAt = createdAt;
    }


}
