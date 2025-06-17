package com.example.blogex.domain.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostStats {
    private Long id;
    private Long cntLike;
    private Long cntComment;

    @Builder
    public PostStats(Long id, Long cntLike, Long cntComment) {
        this.id = id;
        this.cntLike = cntLike;
        this.cntComment = cntComment;
    }

}
