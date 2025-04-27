package com.example.blogex.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CommentCreateResponse {
    private Long id;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public CommentCreateResponse(Long id,
                                 String content,
                                 LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }

}
