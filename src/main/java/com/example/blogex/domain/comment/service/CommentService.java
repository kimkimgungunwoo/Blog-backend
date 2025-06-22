package com.example.blogex.domain.comment.service;


import com.example.blogex.domain.comment.dto.*;
import com.example.blogex.domain.comment.entitiy.Comment;
import com.example.blogex.domain.comment.mapper.CommentMapper;
import com.example.blogex.domain.comment.repository.CommentRepository;
import com.example.blogex.domain.post.entitiy.Post;
import com.example.blogex.domain.post.repository.PostRepository;
import com.example.blogex.domain.user.entitiy.User;
import com.example.blogex.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    /*
    필요 기능
    1. 댓글 생성
    2. 답글 생성
    3. 댓글 불러오기
    4. 댓글 반환
    5. 댓글-답글 트리 반환
     */
    public CommentCreateResponse createComment(CommentCreateRequest commentCreateRequest, Long postId,Long userId) {
        Post post=postRepository.findById(postId).
                orElseThrow(()->new EntityNotFoundException("Post not found"));

        User user=userRepository.findById(userId).
                orElseThrow(()->new EntityNotFoundException("User not found"));

        Comment comment=commentMapper.toEntity(commentCreateRequest);

        comment.setPost(post);
        comment.setUser(user);
        return commentMapper.toResponse(commentRepository.save(comment));

    }

    public CommentCreateResponse createReply(CommentCreateRequest commentCreateRequest, Long parentCommentId,Long userId) {
        Comment parent=commentRepository.findById(parentCommentId).orElseThrow(()->new EntityNotFoundException("Comment not found"));
        Comment reply=commentMapper.toEntity(commentCreateRequest);
        reply.setParentComment(parent);
        reply.setPost(parent.getPost());
        reply.setUser(userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User not found")));
        return commentMapper.toResponse(commentRepository.save(reply));
    }

    //유저의 댓글 목록 불러오기
    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    //댓글 내용으로 댓글 검색
    public List<Comment> getCommentByContent(String content) {
        return commentRepository.findByContentContainingOrderByCreatedAtDesc(content);
    }

    //포스트에 있는 댓글 목록 불러오기
    public List<Comment> getCommentByPostId(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId);
    }

    //포스트에 있는 댓글 좋아요 순 정렬
    public List<Comment> getCommentByPostIdOrderByLikes(Long postId) {
        return commentRepository.findByPostIdOrderByLikes(postId);
    }

    //포스트에 있는 댓글 답글 순 정렬
    public List<Comment> getCommentByPostIdOrderByReplyCount(Long postId) {
        return commentRepository.findByPostIdOrderByChildrenCount(postId);
    }

    //코멘트 FullInfo 로 변환(답글도 댓글 형태로)
    public List<CommentFullInfo> getCommentFullInfoList(List<Comment> commentList) {
        List<Long> ids=commentList.stream()
                .map(Comment::getId)
                .toList();

        List<CommentStats> stats=commentRepository.getCommentStats(ids);

        return commentMapper.toFullInfos(commentList,stats);
    }

    //답글-댓글 형태로 CommentFullInfo 반환
    public List<CommentFullInfo> buildCommentTree (List<Comment> commentList) {
        List<Long> ids=commentList.stream()
                .map(Comment::getId)
                .toList();

        Map<Long,CommentStats>  statsMap=commentRepository.getCommentStats(ids)
                .stream()
                .collect(Collectors.toMap(CommentStats ::getId,s->s));

        Map<Long,List<Comment>> repliesByParent=commentList.stream()
                .filter(c->c.getParentComment()!=null)
                .collect(Collectors.groupingBy(c->c.getParentComment().getId()));

        return commentList.stream()
                .filter(c->c.getParentComment()==null)
                .map(root->{
                    CommentFullInfo parentComment=commentMapper.toFullInfo(root,statsMap.get(root.getId()));

                    List<ReplyFullInfo> replies=repliesByParent
                            .getOrDefault(root.getId(),List.of()).stream()
                                    .sorted(Comparator.comparing(Comment::getCreatedAt).reversed())
                            .map(reply->commentMapper.toReplyFullInfo(reply,statsMap.get(reply.getId())))
                            .toList();



                    parentComment.getCommentInfo().setReplies(replies);

                    return parentComment;
                })
                .toList();


    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }








}
