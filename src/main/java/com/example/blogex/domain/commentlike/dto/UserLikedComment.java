package com.example.blogex.domain.commentlike.dto;


import com.example.blogex.domain.comment.dto.CommentInfo;
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
public class UserLikedComment {
    private CommentInfo commentInfo;
    private LocalDateTime createdAt;

    @Builder
    public UserLikedComment(CommentInfo commentInfo,
                            LocalDateTime createdAt) {
        this.commentInfo = commentInfo;
        this.createdAt = createdAt;
    }

}
