package com.example.blogex.domain.postlike.service;


import com.example.blogex.domain.post.dto.PostStats;
import com.example.blogex.domain.post.entitiy.Post;
import com.example.blogex.domain.post.mapper.PostMapper;
import com.example.blogex.domain.post.repository.PostRepository;
import com.example.blogex.domain.postlike.dto.LikedUserResponse;
import com.example.blogex.domain.postlike.dto.LikedPostByUserResponse;
import com.example.blogex.domain.postlike.entitiy.PostLike;
import com.example.blogex.domain.postlike.repository.PostLikeRepository;
import com.example.blogex.domain.user.entitiy.User;
import com.example.blogex.domain.user.mapper.UserMapper;
import com.example.blogex.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PostLIkeService {
    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserMapper userMapper;

    public void like(Long postId, Long userId) {
        Post post = postRepository.findById(postId).
                orElseThrow(()->new EntityNotFoundException("Post not found"));

        User user = userRepository.findById(userId).
                orElseThrow(()->new EntityNotFoundException("User not found"));

        if(postLikeRepository.existsByPostIdAndLikedById(postId, userId)) {
            postLikeRepository.deleteByPostIdAndLikedById(postId, userId);
        }
        else{
            PostLike postLike = new PostLike(user,post);
            postLikeRepository.save(postLike);
        }
    }

    public List<LikedUserResponse> getLikedUsers(Long postId) {
        Post post= postRepository.findById(postId).
                orElseThrow(()->new EntityNotFoundException("Post not found"));

        List<PostLike> likes=postLikeRepository.findByPostIdOrderByCreatedAtDesc(postId);

        return likes.stream()
                .map(l->{
                    User user=l.getLikedBy();

                    return  LikedUserResponse.builder()
                            .userSimpleInfo(userMapper.toUserSimpleInfo(user))
                            .createdAt(l.getCreatedAt())
                            .build();

                }).toList();
    };

    public List<LikedPostByUserResponse> getLikedPost(Long userId){
        User user = userRepository.findById(userId).
                orElseThrow(()->new EntityNotFoundException("User not found"));

        List<PostLike> likes=postLikeRepository.findByLikedByIdOrderByCreatedAtDesc(userId);

        return likes.stream()
                .map(l->{
                    Post post=l.getPost();
                    PostStats stats=postRepository.findPostStatsById(post.getId());

                    return LikedPostByUserResponse.builder()
                            .postFullInfo(postMapper.toFullInfo(post,stats))
                            .createdAt(l.getCreatedAt())
                            .build();



                }).toList();
    }

}

