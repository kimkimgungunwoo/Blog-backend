package com.example.blogex.domain.commentlike.dto;

import com.example.blogex.domain.user.dto.UserInfo;
import com.example.blogex.domain.user.dto.UserSimpleInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/*
댓글에 좋아요누른 유저 정보
 */


@Getter
@NoArgsConstructor
public class CommentLikedUser {

    private UserSimpleInfo userSimpleInfo;
    private LocalDateTime createdAt;

    @Builder
    public CommentLikedUser(UserSimpleInfo userSimpleInfo,
                            LocalDateTime createdAt) {
        this.userSimpleInfo = userSimpleInfo;
        this.createdAt = createdAt;
    }

}
