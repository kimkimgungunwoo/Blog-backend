package com.example.blogex.domain.hashtag.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class HashtagRankDto {
    private Long id;
    private String tag;
    private Long cnt;

    @Builder
    public HashtagRankDto(Long id,
                          String tag,
                          Long cnt) {
        this.id = id;
        this.tag = tag;
        this.cnt = cnt;
    }

}
