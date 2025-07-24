package com.example.blogex.domain.posttag.dto;


import com.example.blogex.domain.hashtag.dto.HashtagDto;
import com.example.blogex.domain.post.dto.PostInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostTagDto {
    PostInfo postInfo;
    HashtagDto hashtagDto;

    @Builder
    public PostTagDto(PostInfo postInfo, HashtagDto hashtagDto) {
        this.postInfo = postInfo;
        this.hashtagDto = hashtagDto;
    }
}
