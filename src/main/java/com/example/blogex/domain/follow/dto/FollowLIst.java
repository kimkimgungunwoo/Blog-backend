package com.example.blogex.domain.follow.dto;


import com.example.blogex.domain.user.dto.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FollowLIst {
    private List<FollowInfo> followList;
    private UserInfo userInfo;

    @Builder
    public FollowLIst(List<FollowInfo> followList,
                      UserInfo userInfo) {
        this.followList = followList;
        this.userInfo = userInfo;
    }




}
