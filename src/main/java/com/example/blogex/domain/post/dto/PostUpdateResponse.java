package com.example.blogex.domain.post.dto;


import com.example.blogex.domain.hashtag.dto.HashtagDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
public class PostUpdateResponse {
    private Long id;
    private String title;
    private String summary;
    private List<HashtagDto> hashtagList;
    private String thumbnailUrl;
    private String thumbnailOcr;
    private LocalDateTime updatedAt;

    @Builder
    public PostUpdateResponse(Long id,
                              String title,
                              String summary,
                              List<HashtagDto> hashtagList,
                              String thumbnailUrl,
                              String thumbnailOcr,
                              LocalDateTime updatedAt
    ){
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.hashtagList = hashtagList;
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailOcr = thumbnailOcr;
        this.updatedAt = updatedAt;

    }
}
