package com.example.blogex.domain.comment.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ReplyFullInfo {
    private ReplyInfo replyInfo;
    private Long cntLike;

    @Builder
    public ReplyFullInfo(ReplyInfo replyInfo, Long cntLike) {
        this.replyInfo = replyInfo;
        this.cntLike = cntLike;
    }
}
