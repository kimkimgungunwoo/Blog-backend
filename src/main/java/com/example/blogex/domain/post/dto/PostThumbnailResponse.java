package com.example.blogex.domain.post.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostThumbnailResponse {
    private Long postId;
    private String thumbnailUrl;
    private String thumbnailOcr;

    @Builder
    public PostThumbnailResponse(Long postId,
                                 String thumbnailUrl,
                                 String thumbnailOcr) {
        this.postId = postId;
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailOcr = thumbnailOcr;
    }
}
