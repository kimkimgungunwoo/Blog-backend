package com.example.blogex.domain.commentlike.dto;


import com.example.blogex.domain.comment.dto.CommentFullInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/*
유저가 좋아요 누른 댓글 정보
리스트에 담아서, 매핑해서 추출
 */
@Getter
@NoArgsConstructor
public class LikedCommentByUserResponse {
    private CommentFullInfo commentFullInfo;
    private LocalDateTime createdAt;

    @Builder
    public LikedCommentByUserResponse(CommentFullInfo commentFullInfo,
                                      LocalDateTime createdAt) {
        this.commentFullInfo = commentFullInfo;
        this.createdAt = createdAt;
    }

}
