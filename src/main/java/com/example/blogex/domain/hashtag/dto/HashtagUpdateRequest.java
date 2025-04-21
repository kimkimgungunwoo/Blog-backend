package com.example.blogex.domain.hashtag.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


/*
새로운 해쉬태그인지 판단, 추가 등은 service 계층에서 처리
 */
@Getter
@AllArgsConstructor
public class HashtagUpdateRequest {
    private List<String> hashtags;

}
