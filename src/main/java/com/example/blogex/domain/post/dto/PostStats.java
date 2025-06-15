package com.example.blogex.domain.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostStats {
    private Long id;
    private int cntLike;
    private int cntComment;

    @Builder
    public PostStats(Long id, int cntLike, int cntComment) {
        this.id = id;
        this.cntLike = cntLike;
        this.cntComment = cntComment;
    }

}
