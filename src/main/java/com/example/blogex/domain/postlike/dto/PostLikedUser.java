package com.example.blogex.domain.postlike.dto;


import com.example.blogex.domain.post.dto.PostInfo;
import com.example.blogex.domain.user.dto.UserSimpleInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/*
특정 글에 좋아요 누른 유저 정보
리스트에 담아서, 매핑해서 추출
 */
@Getter
@NoArgsConstructor
public class PostLikedUser {
   private UserSimpleInfo userSimpleInfo;
   private LocalDateTime createdAt;

   @Builder
    public PostLikedUser(UserSimpleInfo userSimpleInfo,
                         LocalDateTime createdAt) {
       this.userSimpleInfo = userSimpleInfo;
       this.createdAt = createdAt;
   }

}
