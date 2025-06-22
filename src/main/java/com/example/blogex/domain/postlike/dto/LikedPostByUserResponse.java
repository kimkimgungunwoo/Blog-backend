package com.example.blogex.domain.postlike.dto;


import com.example.blogex.domain.post.dto.PostFullInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//유저가 좋아요 누른 포스트 정보 나중에 리스트에 담아서 추출
@Getter
@NoArgsConstructor
public class LikedPostByUserResponse {
    private PostFullInfo postFullInfo;
    private LocalDateTime createdAt;

    @Builder
    public LikedPostByUserResponse(PostFullInfo postFullInfo,
                                   LocalDateTime createdAt) {
        this.postFullInfo = postFullInfo;
        this.createdAt = createdAt;
    }


}
