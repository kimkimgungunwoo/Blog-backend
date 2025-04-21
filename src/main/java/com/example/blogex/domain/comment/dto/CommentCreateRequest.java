package com.example.blogex.domain.comment.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {
    private Long userId;
    private String content;
    private Long parentCommentId;

    @Builder
    public CommentCreateRequest(Long userId,
                                String content,
                                Long parentCommentId) {
        this.userId = userId;
        this.content = content;
        this.parentCommentId = parentCommentId;
    }


}
