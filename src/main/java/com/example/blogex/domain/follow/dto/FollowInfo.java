package com.example.blogex.domain.follow.dto;


import com.example.blogex.domain.user.dto.UserInfo;
import com.example.blogex.domain.user.dto.UserSimpleInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class FollowInfo {
    private UserSimpleInfo userSimpleInfo;
    private LocalDateTime createdAt;
    private boolean isMutual;

    @Builder
    public FollowInfo(UserSimpleInfo userSimpleInfo,
                      LocalDateTime createdAt,
                      boolean isMutual) {
        this.userSimpleInfo = userSimpleInfo;
        this.createdAt = createdAt;
        this.isMutual = isMutual;
    }
}
