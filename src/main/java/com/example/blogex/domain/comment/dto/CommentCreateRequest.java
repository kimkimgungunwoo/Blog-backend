package com.example.blogex.domain.comment.dto;


import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {
    private String content;


    @Nullable
    private Long parentCommentId;

    @Builder
    public CommentCreateRequest(String content,
                                Long parentCommentId) {

        this.content = content;
        this.parentCommentId = parentCommentId;
    }


}
