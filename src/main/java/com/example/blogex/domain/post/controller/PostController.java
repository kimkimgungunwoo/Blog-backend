package com.example.blogex.domain.post.controller;

import com.example.blogex.domain.block.dto.BlockCreateRequest;
import com.example.blogex.domain.block.dto.BlockCreateResponse;
import com.example.blogex.domain.block.dto.BlockInfo;
import com.example.blogex.domain.block.dto.BlockUpdateResponse;
import com.example.blogex.domain.block.entitiy.Block;
import com.example.blogex.domain.block.service.BlockService;
import com.example.blogex.domain.comment.dto.CommentFullInfo;
import com.example.blogex.domain.comment.entitiy.Comment;
import com.example.blogex.domain.comment.service.CommentService;
import com.example.blogex.domain.hashtag.dto.HashtagDto;
import com.example.blogex.domain.post.dto.*;
import com.example.blogex.domain.post.entitiy.Post;
import com.example.blogex.domain.post.service.PostService;
import com.example.blogex.domain.posttag.entitiy.PostTag;
import com.example.blogex.domain.posttag.service.PostTagService;
import com.example.blogex.domain.user.dto.UserSimpleInfo;
import com.example.blogex.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final PostTagService postTagService;
    private final BlockService blockService;
    private final UserService userService;
    private final CommentService commentService;

    @PostMapping
    public PostCreateResponse createPost(@RequestBody PostCreateRequest request,
                                         @RequestParam Long userId) {
        return postService.createPost(request, userId);
    }

    @GetMapping
    public List<PostFullInfo> getAllPosts() {
        return postService.getPostFullInfoList(postService.getAllPosts());
    }

    @GetMapping("/{postId}")
    public PostContent getPost(@PathVariable Long postId) {
        return postService.getContent(postId);
    }

    // ======== 블럭 관련 ========

    @PostMapping("/{postId}/blocks")
    public BlockCreateResponse createBlock(@PathVariable Long postId, @RequestBody BlockCreateRequest request) {
        return blockService.createBlock(postId, request);
    }

    @PatchMapping("/{postId}/blocks/{blockId}")
    public BlockInfo moveBlock(@PathVariable Long postId, @PathVariable Long blockId, @RequestParam int newIndex) {
        return blockService.moveBlock(postId, blockId, newIndex);
    }

    // ======== 포스트 수정 ========

    @PatchMapping("/{postId}")
    public PostInfo updatePost(@PathVariable Long postId,
                               @RequestBody PostUpdateRequest request) {
        return postService.updatePost(request, postId);
    }

    @PatchMapping("/{postId}/thumbnail")
    public PostInfo updatePostThumbnail(@PathVariable Long postId,
                                        @RequestBody PostThumbnailRequest request) {
        return postService.CreatePostThumbnailRequest(request, postId);
    }

    // ======== 태그 ========

    @PostMapping("/{postId}/tags")
    public PostTag addTag(@PathVariable Long postId, @RequestParam String tag) {
        return postTagService.addTag(postId, tag);
    }

    @DeleteMapping("/{postId}/tags")
    public void deleteTag(@PathVariable Long postId, @RequestParam String tag) {
        postTagService.deleteTag(postId, tag);
    }

    @GetMapping("/{postId}/tags")
    public List<HashtagDto> getTags(@PathVariable Long postId) {
        return postTagService.getTagsForPost(postId);
    }

    // ======== 검색 ========

    @GetMapping("/search/title")
    public List<PostFullInfo> searchByTitle(@RequestParam String title) {
        return postService.getPostFullInfoList(postService.searchByTitle(title));
    }

    @GetMapping("/search/username")
    public List<PostFullInfo> searchByUsername(@RequestParam String username) {
        return postService.getPostFullInfoList(postService.searchByUserName(username));
    }

    @GetMapping("/search/tag")
    public List<PostFullInfo> searchByTag(@RequestParam String tag) {
        return postService.getPostFullInfoList(postService.searchByTag(tag));
    }

    @GetMapping("/search/user")
    public List<PostFullInfo> searchByUserId(@RequestParam Long userId) {
        return postService.getPostFullInfoList(postService.getPostsByUserId(userId));
    }

    @GetMapping("/search/content")
    public List<PostFullInfo> searchByContent(@RequestParam String content) {
        return postService.getPostFullInfoList(postService.searchByContent(content));
    }

    @GetMapping("/search/comment")
    public List<PostFullInfo> searchByCommentContent(@RequestParam String commentContent) {
        return postService.getPostFullInfoList(postService.SearchByCommentContent(commentContent));
    }

    // ======== 인기글 검색 ========

    @GetMapping("/popular")
    public List<PostFullInfo> getPopularPosts() {
        return postService.getPostFullInfoList(postService.getPopularPosts());
    }

    @GetMapping("/popular/user")
    public List<PostFullInfo> getPopularPostsByUserId(@RequestParam Long userId) {
        return postService.getPostFullInfoList(postService.getPopularPostsByUserId(userId));
    }

    @GetMapping("/popular/tag")
    public List<PostFullInfo> getPopularPostsByTag(@RequestParam String tag) {
        return postService.getPostFullInfoList(postService.searchPopularByTag(tag));
    }

    @GetMapping("/popular/title")
    public List<PostFullInfo> getPopularPostsByTitle(@RequestParam String title) {
        return postService.getPostFullInfoList(postService.searchPopularByTitle(title));
    }

    @GetMapping("/popular/content")
    public List<PostFullInfo> getPopularPostsByContent(@RequestParam String content) {
        return postService.getPostFullInfoList(postService.searchPopularPostsByContent(content));
    }

    @GetMapping("/popular/comment")
    public List<PostFullInfo> getPopularPostsByCommentCount() {
        return postService.getPostFullInfoList(postService.getPopularPostsByCommentCnt());
    }

    @GetMapping("/popular/comment/user")
    public List<PostFullInfo> getPopularPostsByCommentCountAndUser(@RequestParam Long userId) {
        return postService.getPostFullInfoList(postService.getPopularPostsByCommentCntByUserId(userId));
    }

    @GetMapping("/popular/combine")
    public List<PostFullInfo> getPopularPostsByCombine() {
        return postService.getPostFullInfoList(postService.findCombinePopularPost());
    }
}
