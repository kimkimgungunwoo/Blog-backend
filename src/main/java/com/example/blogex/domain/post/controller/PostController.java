package com.example.blogex.domain.post.controller;

import com.example.blogex.common.dto.ResultResponse;
import com.example.blogex.domain.block.dto.BlockCreateRequest;
import com.example.blogex.domain.block.dto.BlockCreateResponse;
import com.example.blogex.domain.block.dto.BlockInfo;
import com.example.blogex.domain.block.service.BlockService;
import com.example.blogex.domain.hashtag.dto.HashtagDto;
import com.example.blogex.domain.post.dto.*;
import com.example.blogex.domain.post.service.PostService;
import com.example.blogex.domain.postlike.dto.LikedUserResponse;
import com.example.blogex.domain.postlike.repository.PostLikeRepository;
import com.example.blogex.domain.postlike.service.PostLIkeService;
import com.example.blogex.domain.posttag.entitiy.PostTag;
import com.example.blogex.domain.posttag.repository.PostTagRepository;
import com.example.blogex.domain.posttag.service.PostTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.blogex.common.Code.ResultCode.BASED_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final PostTagService postTagService;
    private final BlockService blockService;
    private final PostLIkeService postLIkeService;
    private final PostTagRepository postTagRepository;

    @PostMapping
    public ResponseEntity<ResultResponse> createPost(@RequestBody PostCreateRequest request,
                                                     @RequestParam Long userId) {
        PostCreateResponse response = postService.createPost(request, userId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, response));
    }

    @GetMapping
    public ResponseEntity<ResultResponse> getAllPosts() {
        List<PostFullInfo> posts = postService.getPostFullInfoList(postService.getAllPosts());
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, posts));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ResultResponse> getPost(@PathVariable Long postId) {
        PostContent post = postService.getContent(postId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, post));
    }

    // ======== 블럭 관련 ========

    @PostMapping("/{postId}/blocks")
    public ResponseEntity<ResultResponse> createBlock(@PathVariable Long postId,
                                                      @RequestBody BlockCreateRequest request) {
        BlockCreateResponse response = blockService.createBlock(postId, request);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, response));
    }

    @PatchMapping("/{postId}/blocks/{blockId}")
    public ResponseEntity<ResultResponse> moveBlock(@PathVariable Long postId,
                                                    @PathVariable Long blockId,
                                                    @RequestParam int newIndex) {
        BlockInfo moved = blockService.moveBlock(postId, blockId, newIndex);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, moved));
    }

    // ======== 포스트 수정 ========

    @PatchMapping("/{postId}")
    public ResponseEntity<ResultResponse> updatePost(@PathVariable Long postId,
                                                     @RequestBody PostUpdateRequest request) {
        PostInfo updated = postService.updatePost(request, postId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, updated));
    }

    @PatchMapping("/{postId}/thumbnail")
    public ResponseEntity<ResultResponse> updatePostThumbnail(@PathVariable Long postId,
                                                              @RequestBody PostThumbnailRequest request) {
        PostInfo updated = postService.CreatePostThumbnailRequest(request, postId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, updated));
    }

    // ======== 태그 ========

    @PostMapping("/{postId}/tags")
    public ResponseEntity<ResultResponse> addTag(@PathVariable Long postId, @RequestParam String tag) {
        PostTag result = postTagService.addTag(postId, tag);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, result));
    }

    @DeleteMapping("/{postId}/tags")
    public ResponseEntity<ResultResponse> deleteTag(@PathVariable Long postId, @RequestParam String tag) {
        postTagService.deleteTag(postId, tag);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS,null));
    }

    @GetMapping("/{postId}/tags")
    public ResponseEntity<ResultResponse> getTags(@PathVariable Long postId) {
        List<HashtagDto> tags = postTagService.getTagsForPost(postId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, tags));
    }

    // ======== 검색/인기/좋아요/해시태그 ========

    @PostMapping("/likes/{postId}")
    public ResponseEntity<ResultResponse> addLike(@PathVariable Long postId, @RequestParam Long userId) {
        postLIkeService.like(postId, userId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS,null));
    }

    @GetMapping("/likes/{postId}")
    public ResponseEntity<ResultResponse> getLikedUser(@PathVariable Long postId) {
        List<LikedUserResponse> users = postLIkeService.getUsersWhoLikedPostx(postId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, users));
    }
}

