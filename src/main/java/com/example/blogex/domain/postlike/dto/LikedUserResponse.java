package com.example.blogex.domain.postlike.dto;


import com.example.blogex.domain.user.dto.UserSimpleInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/*
특정 글에 좋아요 누른 유저 정보
 */
@Getter
@NoArgsConstructor
public class LikedUserResponse {
   private UserSimpleInfo userSimpleInfo;
   private LocalDateTime createdAt;

   @Builder
    public LikedUserResponse(UserSimpleInfo userSimpleInfo,
                             LocalDateTime createdAt) {
       this.userSimpleInfo = userSimpleInfo;
       this.createdAt = createdAt;
   }

}
