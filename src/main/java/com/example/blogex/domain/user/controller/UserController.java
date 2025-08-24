package com.example.blogex.domain.user.controller;

import com.example.blogex.common.dto.ResultResponse;
import com.example.blogex.domain.commentlike.dto.LikedCommentByUserResponse;
import com.example.blogex.domain.commentlike.service.CommentLikeService;
import com.example.blogex.domain.follow.dto.FollowInfo;
import com.example.blogex.domain.follow.service.FollowService;
import com.example.blogex.domain.hashtag.dto.HashtagRankDto;
import com.example.blogex.domain.hashtag.service.HashtagService;
import com.example.blogex.domain.post.dto.PostFullInfo;
import com.example.blogex.domain.post.service.PostService;
import com.example.blogex.domain.postlike.dto.LikedPostByUserResponse;
import com.example.blogex.domain.postlike.dto.LikedUserResponse;
import com.example.blogex.domain.postlike.entitiy.PostLike;
import com.example.blogex.domain.postlike.service.PostLIkeService;
import com.example.blogex.domain.user.dto.UserCreateRequest;
import com.example.blogex.domain.user.dto.UserCreateResponse;
import com.example.blogex.domain.user.dto.UserLoginRequest;
import com.example.blogex.domain.user.dto.UserProfile;
import com.example.blogex.domain.user.entitiy.User;
import com.example.blogex.domain.user.service.AuthService;
import com.example.blogex.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.blogex.common.Code.ResultCode.BASED_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final PostLIkeService postLIkeService;
    private final CommentLikeService commentLikeService;
    private final FollowService followService;
    private final PostService postService;
    private final HashtagService hashtagService;
    private final AuthService authService;

    // =========================================================
    // 1) 유저 생성 / 프로필 (Users)
    // =========================================================

    // 유저 생성
    @PostMapping
    public ResponseEntity<ResultResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        authService.signUp(userCreateRequest);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS,null));
    }

    @PostMapping("/login")
    public ResponseEntity<ResultResponse> login(
            @RequestBody UserLoginRequest userLoginRequest,
            HttpServletRequest request) {
        Long userId= authService.login(userLoginRequest);
        HttpSession httpSession=request.getSession(true);
        httpSession.setAttribute("LOGIN_USER_ID",userId);
        System.out.println("SESSION ID = " + httpSession.getId());

        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS,null));
    }

    @PostMapping("/logout")
    public ResponseEntity<ResultResponse> logout(HttpServletRequest request) {
        var s = request.getSession(false);
        if (s != null) s.invalidate();
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS,null));
    }


    // 유저 프로필 조회
    @GetMapping("/{userId}")
    public ResponseEntity<ResultResponse> getUser(@PathVariable Long userId) {
        UserProfile profile = userService.getUserProfile(userId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, profile));
    }

    // =========================================================
    // 2) 피드 (Feed)
    // =========================================================

    // 유저 피드 조회
    @GetMapping("/{userId}/feed")
    public ResponseEntity<ResultResponse> getFeed(@PathVariable Long userId) {
        List<PostFullInfo> feed = postService.getPostFullInfoList(postService.getFeed(userId));
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, feed));
    }

    // =========================================================
    // 3) 좋아요 (Likes)
    // =========================================================

    // 유저가 좋아요 누른 게시글
    @GetMapping("/likes/post/{userId}")
    public ResponseEntity<ResultResponse> getPostLikes(@PathVariable Long userId) {
        List<LikedPostByUserResponse> posts = postLIkeService.getPostsLikedByUser(userId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, posts));
    }

    // 유저가 좋아요 누른 댓글
    @GetMapping("/likes/comment/{userId}")
    public ResponseEntity<ResultResponse> getCommentLikes(@PathVariable Long userId) {
        List<LikedCommentByUserResponse> comments = commentLikeService.getCommentsLikedByUser(userId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, comments));
    }

    // =========================================================
    // 4) 팔로우 (Follow)
    // =========================================================

    // 팔로우/언팔로우
    @PostMapping("/follow/{targetUserId}")
    public ResponseEntity<ResultResponse> follow(@PathVariable Long targetUserId,
                                                 @io.swagger.v3.oas.annotations.Parameter(hidden = true)
                                                 @SessionAttribute(name ="LOGIN_USER_ID",required = true ) Long userId) {
        followService.follow(userId, targetUserId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, null));
    }

    // 내가 팔로우 중인 유저 목록
    @GetMapping("/follow/{userId}/follow")
    public ResponseEntity<ResultResponse> getFollows(@PathVariable Long userId) {
        List<FollowInfo> follows = followService.getFollows(userId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, follows));
    }

    // 나를 팔로우하는 유저 목록
    @GetMapping("/follow/{userId}/follower")
    public ResponseEntity<ResultResponse> getFollowers(@PathVariable Long userId) {
        List<FollowInfo> followers = followService.getFollowers(userId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, followers));
    }

    // =========================================================
    // 5) 해시태그 (Hashtags)
    // =========================================================

    // 내가 자주 사용하는 해시태그 순위
    @GetMapping("/hashtag/{userId}")
    public ResponseEntity<ResultResponse> getUserHashTagRank(@PathVariable Long userId) {
        List<HashtagRankDto> rank = hashtagService.getUserHashtagRank(userId);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS, rank));
    }
}
