package com.example.blogex.domain.comment.dto;

import com.example.blogex.domain.user.dto.UserSimpleInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ReplyInfo {
    private Long id;
    private UserSimpleInfo userSimpleInfo;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public ReplyInfo(Long id,
                     UserSimpleInfo userSimpleInfo,
                     String content,
                     LocalDateTime createdAt) {
        this.id = id;
        this.userSimpleInfo = userSimpleInfo;
        this.content = content;
        this.createdAt = createdAt;
    }
}
