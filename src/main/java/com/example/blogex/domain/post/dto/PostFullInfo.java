package com.example.blogex.domain.post.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostFullInfo {

    private PostInfo postInfo;
    private Long cntLike;
    private Long cntComment;


}
