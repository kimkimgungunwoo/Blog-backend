package com.example.blogex.domain.post.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostThumbnailResponse {
    private Long postId;
    private String thumbnailUrl;
}
