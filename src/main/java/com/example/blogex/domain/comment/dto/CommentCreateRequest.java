package com.example.blogex.domain.comment.dto;


import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {
    private String content;


}
