package com.example.blogex.domain.hashtag.controller;


import com.example.blogex.domain.hashtag.dto.HashtagDto;
import com.example.blogex.domain.hashtag.dto.HashtagRankDto;
import com.example.blogex.domain.hashtag.service.HashtagService;
import com.example.blogex.domain.post.service.PostService;
import com.example.blogex.domain.posttag.service.PostTagService;
import com.example.blogex.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hashtag")
public class HashtagController {
    private final HashtagService hashtagService;
    private final PostService postService;
    private final PostTagService postTagService;
    private final UserService userService;


    @PostMapping()
    public HashtagDto createHashTag(@RequestParam String tag) {
        return hashtagService.createHashtag(tag);
    }

    @GetMapping("/rank")
    public List<HashtagRankDto> getHashtagRank() {
        return hashtagService.getHashTagRank();
    }





}
