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
    private LocalDateTime createAt;
    private boolean isMutual;

    @Builder
    public FollowInfo(UserSimpleInfo userSimpleInfo,
                      LocalDateTime createAt,
                      boolean isMutual) {
        this.userSimpleInfo = userSimpleInfo;
        this.createAt = createAt;
        this.isMutual = isMutual;
    }
}
