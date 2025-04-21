package com.example.blogex.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSimpleInfo {
    /*
    게시글에서 보이는 닉네임이나, 댓글에서 보이는 작성자, 팔로우목록 등에서
    닉네임만 보이게 하는 용도
     */

    private Long id;
    private String username;
    private String profileImageUrl;

    @Builder
    public UserSimpleInfo(Long id, String username, String profileImageUrl) {
        this.id = id;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
    }
}
