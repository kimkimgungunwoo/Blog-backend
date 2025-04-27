package com.example.blogex.domain.post.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostCreateRequest {
    private String title;


    @Builder
    public PostCreateRequest( String title, Long userid) {
        this.title = title;
    }
}
