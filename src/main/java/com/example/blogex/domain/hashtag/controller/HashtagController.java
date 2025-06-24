package com.example.blogex.domain.hashtag.controller;


import com.example.blogex.common.dto.ResultResponse;
import com.example.blogex.domain.hashtag.dto.HashtagDto;
import com.example.blogex.domain.hashtag.dto.HashtagRankDto;
import com.example.blogex.domain.hashtag.service.HashtagService;
import com.example.blogex.domain.post.service.PostService;
import com.example.blogex.domain.posttag.service.PostTagService;
import com.example.blogex.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.blogex.common.Code.ResultCode.BASED_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hashtag")
public class HashtagController {

    private final HashtagService hashtagService;
    private final PostService postService;
    private final PostTagService postTagService;
    private final UserService userService;

    // 해시태그 생성
    @PostMapping
    public ResponseEntity<ResultResponse> createHashTag(@RequestParam String tag) {
        HashtagDto result = hashtagService.createHashtag(tag);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, result));
    }

    // 해시태그 랭킹 조회
    @GetMapping("/rank")
    public ResponseEntity<ResultResponse> getHashtagRank() {
        List<HashtagRankDto> ranks = hashtagService.getHashTagRank();
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, ranks));
    }
}