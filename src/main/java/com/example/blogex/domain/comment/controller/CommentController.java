package com.example.blogex.domain.comment.controller;


import com.example.blogex.common.dto.ResultResponse;
import com.example.blogex.domain.comment.dto.CommentCreateRequest;
import com.example.blogex.domain.comment.dto.CommentCreateResponse;
import com.example.blogex.domain.comment.dto.CommentFullInfo;
import com.example.blogex.domain.comment.mapper.CommentMapper;
import com.example.blogex.domain.comment.repository.CommentRepository;
import com.example.blogex.domain.comment.service.CommentService;
import com.example.blogex.domain.commentlike.dto.LikedUserResponse;
import com.example.blogex.domain.commentlike.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.blogex.common.Code.ResultCode.BASED_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;
    private final CommentLikeService commentLikeService;
    private final CommentMapper commentMapper;

    // 댓글 작성
    @PostMapping("/post/{postId}")
    public ResponseEntity<ResultResponse> createComment(@PathVariable Long postId,
                                                        @RequestParam Long userId,
                                                        @RequestBody CommentCreateRequest request) {
        CommentCreateResponse createResponse = commentService.createComment(request, postId, userId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, createResponse));
    }

    // 대댓글 작성
    @PostMapping("/{commentId}/reply")
    public ResponseEntity<ResultResponse> replyToComment(@PathVariable Long commentId,
                                                         @RequestParam Long userId,
                                                         @RequestBody CommentCreateRequest request) {
        CommentCreateResponse replyResponse = commentService.createReply(request, commentId, userId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, replyResponse));
    }

    // 게시글 댓글 전체 조회 (트리)
    @GetMapping("/post/{postId}")
    public ResponseEntity<ResultResponse> getAllComments(@PathVariable Long postId) {
        List<CommentFullInfo> comments = commentService.buildCommentTree(
                commentService.getCommentByPostId(postId));
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, comments));
    }

    // 좋아요 기준 정렬
    @GetMapping("/post/{postId}/popular/likes")
    public ResponseEntity<ResultResponse> getCommentsByLikes(@PathVariable Long postId) {
        List<CommentFullInfo> comments = commentService.buildCommentTree(
                commentService.getCommentByPostIdOrderByLikes(postId));
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, comments));
    }

    // 대댓글 수 기준 정렬
    @GetMapping("/post/{postId}/popular/replies")
    public ResponseEntity<ResultResponse> getCommentsByReplies(@PathVariable Long postId) {
        List<CommentFullInfo> comments = commentService.buildCommentTree(
                commentService.getCommentByPostIdOrderByReplyCount(postId));
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, comments));
    }

    // 유저 기준 댓글 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<ResultResponse> getCommentsByUser(@PathVariable Long userId) {
        List<CommentFullInfo> comments = commentService.getCommentFullInfoList(
                commentService.getCommentsByUserId(userId));
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, comments));
    }

    // 댓글 내용으로 검색
    @GetMapping("/search")
    public ResponseEntity<ResultResponse> searchComments(@RequestParam String search) {
        List<CommentFullInfo> comments = commentService.getCommentFullInfoList(
                commentService.getCommentByContent(search));
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, comments));
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResultResponse> deleteComment(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS,null));
    }

    // ===== 좋아요 =====
    @PostMapping("/likes/{commentId}")
    public ResponseEntity<ResultResponse> likes(@PathVariable Long commentId,
                                                @RequestParam Long userId) {
        commentLikeService.like(commentId, userId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS,null));
    }

    @GetMapping("/likes/{commentId}")
    public ResponseEntity<ResultResponse> getLikedUsers(@PathVariable Long commentId) {
        List<LikedUserResponse> users = commentLikeService.getUsersWhoLikedComment(commentId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, users));
    }


}
