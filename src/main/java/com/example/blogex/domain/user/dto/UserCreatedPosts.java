package com.example.blogex.domain.user.dto;


import com.example.blogex.domain.post.dto.PostInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserCreatedPosts {
    private UserInfo userInfo;
    private List<PostInfo> postInfos;

    @Builder
    public UserCreatedPosts(UserInfo userInfo, List<PostInfo> postInfos) {
        this.userInfo = userInfo;
        this.postInfos = postInfos;
    }

}
