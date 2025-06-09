package com.example.blogex.domain.comment.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class CommentFullInfo {
    private CommentInfo commentInfo;
    private Long cntLike;

    @Builder
    public CommentFullInfo(CommentInfo commentInfo, Long cntLike) {
        this.commentInfo = commentInfo;
        this.cntLike = cntLike;
    }
}

