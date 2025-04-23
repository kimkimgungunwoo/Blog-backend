package com.example.blogex.domain.post.dto;


import com.example.blogex.domain.hashtag.dto.HashtagDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostUpdateRequest {
    private String title;
    private String summary;
    private String thumbnailUrl;
    private List<HashtagDto> hashtahList;

    @Builder
    public  PostUpdateRequest(String title,
                              String summary,
                              String thumbnailUrl,
                              List<HashtagDto> hashtahList) {
        this.title = title;
        this.summary = summary;
        this.thumbnailUrl = thumbnailUrl;
        this.hashtahList = hashtahList;
    }


}

