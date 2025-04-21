package com.example.blogex.domain.posttag.dto;


import com.example.blogex.domain.post.dto.PostInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class HashtagSearchResponse {
    private List<String> tags;
    private List<PostInfo> posts;

    @Builder
    public HashtagSearchResponse(List<String> tags,
                                 List<PostInfo> posts) {
        this.tags = tags;
        this.posts = posts;
    }
}
