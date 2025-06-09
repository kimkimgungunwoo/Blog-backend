package com.example.blogex.domain.post.dto;


import com.example.blogex.domain.user.dto.UserSimpleInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
포스트 미리보기, 게시글 정보, 작성자 정보
 */
@Getter
@NoArgsConstructor
public class PostInfo {
    private Long id;
    private UserSimpleInfo userSimpleInfo;
    private String title;
    private String previewImageUrl;
    private String previewImageOcr;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Builder
    public PostInfo(
            Long id,
            UserSimpleInfo userSimpleInfo,
            String title,
            String previewImageOcr,
            String previewImageUrl,
            LocalDateTime createdAt,
            LocalDateTime updatedAt

    ){
        this.id = id;
        this.userSimpleInfo = userSimpleInfo;
        this.title = title;
        this.previewImageOcr = previewImageOcr;
        this.previewImageUrl = previewImageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

}
