package com.example.blogex.domain.comment.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {
    private String content;
    private Long parentCommentId;

    @Builder
    public CommentCreateRequest(String content,
                                Long parentCommentId) {

        this.content = content;
        this.parentCommentId = parentCommentId;
    }


}
