package com.example.blogex.domain.post.controller;

import com.example.blogex.common.dto.ResultResponse;
import com.example.blogex.domain.block.dto.*;
import com.example.blogex.domain.block.service.BlockService;
import com.example.blogex.domain.hashtag.dto.HashtagDto;
import com.example.blogex.domain.post.dto.*;
import com.example.blogex.domain.post.service.PostService;
import com.example.blogex.domain.postlike.dto.LikedUserResponse;
import com.example.blogex.domain.postlike.repository.PostLikeRepository;
import com.example.blogex.domain.postlike.service.PostLIkeService;
import com.example.blogex.domain.posttag.dto.PostTagDto;
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

    // =========================================================
    // 1) 포스트 생성/조회/수정 (Posts: Create / Read / Update)
    // =========================================================

    // 포스트 생성
    @PostMapping
    public ResponseEntity<ResultResponse> createPost(@RequestBody PostCreateRequest request,
                                                     @RequestParam Long userId) {
        PostCreateResponse response = postService.createPost(request, userId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, response));
    }

    // 전체 포스트 조회
    @GetMapping
    public ResponseEntity<ResultResponse> getAllPosts() {
        List<PostFullInfo> posts = postService.getPostFullInfoList(postService.getAllPosts());
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, posts));
    }

    // 단일 포스트 조회 (본문)
    @GetMapping("/{postId}")
    public ResponseEntity<ResultResponse> getPost(@PathVariable Long postId) {
        PostContent post = postService.getContent(postId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, post));
    }

    // 포스트 수정
    @PatchMapping("/{postId}")
    public ResponseEntity<ResultResponse> updatePost(@PathVariable Long postId,
                                                     @RequestBody PostUpdateRequest request) {
        PostInfo updated = postService.updatePost(request, postId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, updated));
    }

    // 포스트 썸네일 수정
    @PatchMapping("/{postId}/thumbnail")
    public ResponseEntity<ResultResponse> updatePostThumbnail(@PathVariable Long postId,
                                                              @RequestBody PostThumbnailRequest request) {
        PostInfo updated = postService.CreatePostThumbnailRequest(request, postId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, updated));
    }

    // =========================================================
    // 2) 블록 편집 (Blocks)
    // =========================================================

    // 블록 생성
    @PostMapping("/{postId}/blocks")
    public ResponseEntity<ResultResponse> createBlock(@PathVariable Long postId,
                                                      @RequestBody BlockCreateRequest request) {
        BlockCreateResponse response = blockService.createBlock(postId, request);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, response));
    }

    // 블록 이동
    @PatchMapping("/{postId}/blocks/{blockId}/move")
    public ResponseEntity<ResultResponse> moveBlock(@PathVariable Long postId,
                                                    @PathVariable Long blockId,
                                                    @RequestParam int newIndex) {
        BlockInfo moved = blockService.moveBlock(postId, blockId, newIndex);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, moved));
    }

    // 블록 업데이트
    @PatchMapping("/{postId}/block/{blockId}/update")
    public ResponseEntity<ResultResponse> updateBlock(@PathVariable Long postId,
                                                      @PathVariable Long blockId,
                                                      @RequestBody BlockUpdateRequest request) {
        BlockUpdateResponse blockUpdateResponse = blockService.updateBlock(blockId, request);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, blockUpdateResponse));
    }

    // =========================================================
    // 3) 태그 (Tags)
    // =========================================================

    // 태그 추가
    @PostMapping("/{postId}/tags")
    public ResponseEntity<ResultResponse> addTag(@PathVariable Long postId, @RequestParam String tag) {
        PostTagDto result = postTagService.addTag(postId, tag);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, result));
    }

    // 태그 삭제
    @DeleteMapping("/{postId}/tags")
    public ResponseEntity<ResultResponse> deleteTag(@PathVariable Long postId, @RequestParam String tag) {
        postTagService.deleteTag(postId, tag);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, null));
    }

    // 태그 조회
    @GetMapping("/{postId}/tags")
    public ResponseEntity<ResultResponse> getTags(@PathVariable Long postId) {
        List<HashtagDto> tags = postTagService.getTagsForPost(postId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, tags));
    }

    // =========================================================
    // 4) 좋아요 (Likes)
    // =========================================================

    // 좋아요 등록/토글
    @PostMapping("/likes/{postId}")
    public ResponseEntity<ResultResponse> addLike(@PathVariable Long postId, @RequestParam Long userId) {
        postLIkeService.like(postId, userId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, null));
    }

    // 좋아요한 유저 목록
    @GetMapping("/likes/{postId}")
    public ResponseEntity<ResultResponse> getLikedUser(@PathVariable Long postId) {
        List<LikedUserResponse> users = postLIkeService.getUsersWhoLikedPost(postId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, users));
    }

    // =========================================================
    // 5) 검색 / 유저별 조회 (Search & Filter)
    // =========================================================

    // 제목으로 검색
    @GetMapping("/search/title")
    public ResponseEntity<ResultResponse> searchTitle(@RequestParam String title) {
        List<PostFullInfo> posts = postService.getPostFullInfoList(postService.searchByTitle(title));
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, posts));
    }

    // 내용으로 검색
    @GetMapping("/search/content")
    public ResponseEntity<ResultResponse> searchContent(@RequestParam String content) {
        List<PostFullInfo> posts = postService.getPostFullInfoList(postService.searchByContent(content));
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, posts));
    }

    // 유저가 작성한 포스트 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<ResultResponse> getUser(@PathVariable Long userId) {
        List<PostFullInfo> posts = postService.getPostFullInfoList(postService.getPostsByUserId(userId));
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, posts));
    }
}
