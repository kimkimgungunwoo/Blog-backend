package com.example.blogex.domain.commentlike.service;

import com.example.blogex.domain.comment.dto.CommentStats;
import com.example.blogex.domain.comment.entitiy.Comment;
import com.example.blogex.domain.comment.mapper.CommentMapper;
import com.example.blogex.domain.comment.repository.CommentRepository;
import com.example.blogex.domain.commentlike.dto.LikedUserResponse;
import com.example.blogex.domain.commentlike.dto.LikedCommentByUserResponse;
import com.example.blogex.domain.commentlike.entitiy.CommentLike;
import com.example.blogex.domain.commentlike.repository.CommentLikeRepository;
import com.example.blogex.domain.user.dto.UserSimpleInfo;
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
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;
    private final UserMapper userMapper;

    /*
        필요 기능
        1. 좋아요 (다시 누를 경우 삭제)
        2. 좋아요 누른 댓글들 불러오기
        3. 좋아요 누른 사람들 불러오기
    
     */
    public void like(Long commentId,Long userId){
        Comment comment=commentRepository.findById(commentId)
                .orElseThrow(()->new EntityNotFoundException("User Not Found"));

        User user=userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("User Not Found"));
        if (commentLikeRepository.existsByLikedCommentIdAndLikedById(commentId,userId)){
            commentLikeRepository.deleteByLikedCommentIdAndLikedById(commentId,userId);
        }
        else {
            CommentLike commentLike=new CommentLike(user,comment);
            commentLikeRepository.save(commentLike);
        }
    }

    public List<LikedCommentByUserResponse> getCommentsLikedByUser(Long userId){
        User user=userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("User Not Found"));


        List<CommentLike> likes=commentLikeRepository.findByLikedByIdOrderByCreatedAtDesc(userId);

        return likes.stream()
                .map(l->{
                    Comment comment=l.getLikedComment();
                    CommentStats stats=commentRepository.getCommentStatsById(comment.getId());
                    return LikedCommentByUserResponse.builder()
                            .commentFullInfo(commentMapper.toFullInfo(comment,stats))
                            .createdAt(l.getCreatedAt())
                            .build();
                }).toList();
    }

    public List<LikedUserResponse> getUsersWhoLikedComment(Long commentId){
        Comment comment =commentRepository.findById(commentId)
                .orElseThrow(()->new EntityNotFoundException("Comment Not Found"));

        List<CommentLike> likes=commentLikeRepository.findByLikedCommentIdOrderByCreatedAtDesc(commentId);

        return likes.stream()
                .map(l->{
                    User user =l.getLikedBy();
                    UserSimpleInfo userSimpleInfo=userMapper.toUserSimpleInfo(user);

                    return  LikedUserResponse.builder()
                            .userSimpleInfo(userSimpleInfo)
                            .createdAt(l.getCreatedAt())
                            .build();
                }).toList();

    }

}
