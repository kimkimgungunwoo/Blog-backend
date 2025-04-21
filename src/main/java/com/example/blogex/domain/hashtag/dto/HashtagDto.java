package com.example.blogex.domain.hashtag.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

public class HashtagDto {
    private Long id;
    private String tag;

    @Builder
    public HashtagDto(Long id,
                      String tag) {
        this.id = id;
        this.tag = tag;
    }

}
