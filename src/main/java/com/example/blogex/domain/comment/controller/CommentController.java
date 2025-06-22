package com.example.blogex.domain.comment.controller;


import com.example.blogex.domain.comment.dto.CommentCreateRequest;
import com.example.blogex.domain.comment.dto.CommentCreateResponse;
import com.example.blogex.domain.comment.dto.CommentFullInfo;
import com.example.blogex.domain.comment.service.CommentService;
import com.example.blogex.domain.commentlike.dto.LikedUserResponse;
import com.example.blogex.domain.commentlike.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;
    private final CommentLikeService commentLikeService;

    // 댓글 작성
    @PostMapping("/post/{postId}")
    public CommentCreateResponse createComment(@PathVariable Long postId,
                                               @RequestParam Long userId,
                                               @RequestBody CommentCreateRequest request) {
        return commentService.createComment(request, postId, userId);
    }

    // 대댓글 작성
    @PostMapping("/{commentId}/reply")
    public CommentCreateResponse replyToComment(@PathVariable Long commentId,
                                                @RequestParam Long userId,
                                                @RequestBody CommentCreateRequest request) {
        return commentService.createReply(request, commentId, userId);
    }

    // 게시글 댓글 전체 조회 (트리)
    @GetMapping("/post/{postId}")
    public List<CommentFullInfo> getAllComments(@PathVariable Long postId) {
        return commentService.buildCommentTree(commentService.getCommentByPostId(postId));
    }

    // 좋아요 기준 정렬
    @GetMapping("/post/{postId}/popular/likes")
    public List<CommentFullInfo> getCommentsByLikes(@PathVariable Long postId) {
        return commentService.buildCommentTree(commentService.getCommentByPostIdOrderByLikes(postId));
    }

    // 대댓글 수 기준 정렬
    @GetMapping("/post/{postId}/popular/replies")
    public List<CommentFullInfo> getCommentsByReplies(@PathVariable Long postId) {
        return commentService.buildCommentTree(commentService.getCommentByPostIdOrderByReplyCount(postId));
    }

    // 유저 기준 댓글 조회
    @GetMapping("/user/{userId}")
    public List<CommentFullInfo> getCommentsByUser(@PathVariable Long userId) {
        return commentService.getCommentFullInfoList(commentService.getCommentsByUserId(userId));
    }

    // 댓글 내용으로 검색
    @GetMapping("/search")
    public List<CommentFullInfo> searchComments(@RequestParam String search) {
        return commentService.getCommentFullInfoList(commentService.getCommentByContent(search));
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.delete(commentId);
    }


    //=====좋아요====
    @PostMapping("/likes/{commentId}")
    public void likes(@PathVariable Long commentId, @RequestParam Long userId) {
        commentLikeService.like(commentId, userId);
    }

    @GetMapping("/likes/{commentId}")
    public List<LikedUserResponse> getLikedUsers(@PathVariable Long commentId) {
        return commentLikeService.getUsersWhoLikedComment(commentId);
    }


}
