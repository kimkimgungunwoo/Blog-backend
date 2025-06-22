package com.example.blogex.domain.post.service;

import com.example.blogex.domain.block.dto.BlockInfo;
import com.example.blogex.domain.block.repository.BlockRepository;
import com.example.blogex.domain.block.service.BlockService;
import com.example.blogex.domain.comment.dto.CommentFullInfo;
import com.example.blogex.domain.comment.service.CommentService;
import com.example.blogex.domain.hashtag.dto.HashtagDto;
import com.example.blogex.domain.hashtag.entitiy.HashTag;
import com.example.blogex.domain.post.dto.*;
import com.example.blogex.domain.post.entitiy.Post;
import com.example.blogex.domain.post.mapper.PostMapper;
import com.example.blogex.domain.post.repository.PostRepository;
import com.example.blogex.domain.posttag.service.PostTagService;
import com.example.blogex.domain.user.dto.UserSimpleInfo;
import com.example.blogex.domain.user.entitiy.User;
import com.example.blogex.domain.user.mapper.UserMapper;
import com.example.blogex.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final BlockRepository blockRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PostMapper postMapper;
    private final BlockService blockService;
    private final CommentService commentService;
    private final PostTagService postTagService;
    
    //일부 기능 추후 추가

    //포스트 생성
    public PostCreateResponse createPost(PostCreateRequest postCreateRequest, Long userId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("User not found"));

        Post post=new Post();
        post.setTitle(postCreateRequest.getTitle());
        post.setUser(user);
        Post savedPost=postRepository.save(post);

        return PostCreateResponse.builder().
                id(savedPost.getId()).
                title(post.getTitle()).
                userInfo(userMapper.toUserInfo(user)).
                createdAt(LocalDateTime.now()).
                build();
    }


    //포스트썸네일 생성
    public PostInfo CreatePostThumbnailRequest(PostThumbnailRequest postThumbnailRequest, Long postId) {
        Post post=postRepository.findById(postId).orElseThrow(()->new EntityNotFoundException("Post not found"));

        post.setPreviewImage(postThumbnailRequest.getThumbnailUrl());
        Post savedPost=postRepository.save(post);

        return postMapper.toInfo(savedPost);

    }

    //포스트 변경
    public PostInfo updatePost(PostUpdateRequest postUpdateRequest, Long postId) {
        Post post=postRepository.findById(postId).orElseThrow(()->new EntityNotFoundException("Post not found"));

        post.setSummation(postUpdateRequest.getSummary());
        post.setTitle(postUpdateRequest.getTitle());
        Post savedPost=postRepository.save(post);

        return postMapper.toInfo(savedPost);
    }



    //포스트 리스트 불러오기 기능들
    public List<Post> getAllPosts(){
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Post> getPostsByUserId(Long userId){
        return postRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Post> getPopularPosts(){
        return postRepository.findAllPopularPost();
    }
    public List<Post> getPopularPostsByUserId(Long userId){
        return postRepository.findPopularPostByUserId(userId);
    }

    public List<Post> getPopularPostsByCommentCnt(){
        return postRepository.findPopularPostByComments();
    }

    public List<Post> getPopularPostsByCommentCntByUserId(Long userId){
        return postRepository.findPopularPostByCommentByUserId(userId);
    }

    public List<Post> searchByContent(String content){
        return postRepository.findPostByContentLike(content);
    }

    public List<Post> searchPopularPostsByContent(String content){
        return postRepository.findPopularPostByContentLike(content);
    }

    public List<Post> findCombinePopularPost(){
        return postRepository.findByCombinePopularity();
    }

    public List<Post> SearchByCommentContent(String content){
        return postRepository.findPostByCommentContentLikeOrderByDesc(content);
    }

    public List<Post> searchByTitle(String title){
        return postRepository.findByTitleContainingOrderByCreatedAtDesc(title);
    }

    public List<Post> searchPopularByTitle(String title){
        return postRepository.findPopularPostByTitleLike(title);
    }

    public List<Post> searchByUserName(String username){
        return postRepository.findByUsernameContainingOrderByCreateAtDesc(username);
    }

    public List<Post> searchByTag(String tag){
        return postRepository.findByHashtagContainingOrderByCreateAtDesc(tag);
    }
    
    public List<Post> searchPopularByTag(String tag){
        return postRepository.findByTagContainingOrderByLikeDesc(tag);
    }

    //PostFullInfo 로 변경
    public List<PostFullInfo> getPostFullInfoList(List<Post> posts){
        List<Long> ids=posts.stream()
                .map(Post::getId)
                .toList();

        List<PostStats> stats=postRepository.findPostStatsByIds(ids);

        return postMapper.toFullInfos(posts,stats);


    }

    public PostContent getContent(Long postId){
        List<BlockInfo> blocks=blockService.getBlocksByPostId(postId);
        List<CommentFullInfo> comments=commentService.buildCommentTree(commentService.getCommentByPostId(postId));
        List<HashtagDto> hashTags=postTagService.getTagsForPost(postId);
        PostFullInfo postFullInfo=postMapper.toFullInfo(postRepository.findById(postId).orElseThrow(),postRepository.findPostStatsById(postId));


        return PostContent.builder()
                .blockList(blocks)
                .commentList(comments)
                .postInfo(postFullInfo)
                .hashtagList(hashTags)
                .build();
    }



}
