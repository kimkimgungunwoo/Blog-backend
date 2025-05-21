package com.example.blogex.domain.commentlike.repository;

import com.example.blogex.domain.commentlike.entitiy.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    //코멘트에 달린 좋아요리스트 불러오기
    //추후에 서비스 계층에서 매핑해서 사용
    List<CommentLike> findByLikedCommentIdOrderByCreatedAtDesc(Long id);
    
    //유저가좋야요누른코멘트좋야오리스트불러오기
    //추후에 서비스 계층에서 매핑해서 사용
    List<CommentLike> findByLikedByIdOrderByCreatedAtDesc(Long id);

    //댓글에 달린 좋아요 개수
    long countByLikedCommentId(Long id);

    //이미 유저가 좋아요 눌렀는지 확인하는 용도
    boolean existsByLikedCommentIdAndLikedById(Long commentId,Long userId);
    
    //좋아요 삭제 용도
    void deleteByLikedCommentIdAndLikedById(Long commentId,Long userId);

}
