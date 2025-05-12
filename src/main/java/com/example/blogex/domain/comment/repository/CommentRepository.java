package com.example.blogex.domain.comment.repository;

import com.example.blogex.domain.comment.entitiy.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //유저댓글 전부 불러오기
    //댓글기록은 최신순이 기본
    List<Comment> findByUserIdOrderByCreateAtDesc(Long userId);
    //댓글 일부 내용으로 검색
    List<Comment> findByContentContainingOrderByCreateAtDesc(String commentText);

    //포스트에 있는 댓글들 불러오기
    //포스트에 있는 댓글들은 생성시간 오름차순
    List<Comment> findByPostIdOrderByCreateAtAsc(Long postId);
    
    //포스트에 있는 댓글들 생성시간 내림차순 정렬
    List<Comment> findByPostIdOrderByCreateAtDesc(Long postId);

    //댓글에 있는 답글들 불러오기
    //답글들도 기본적으로 생성시간 오름차순
    List<Comment> findByParentCommentIdOrderByCreateAtAsc(Long parentCommentId);


    //댓글 좋아요순 정렬
    //쿼리 사용
    @Query("""
    SELECT c
    FROM Comment c
    left JOIN c.likes l
    WHERE c.post.id = :postId
    GROUP By c
    ORDER BY count(l) DESC""")
    List<Comment> findByPostIdOrderOrderByLikes(@Param("postId") Long postId);

    //댓글 답글 순 정렬
    //쿼리사용
    @Query("""
    SELECT c
    from Comment c
    left JOIN c.children ch
    WHERE c.post.id=:postId
    GROUP BY c
    ORDER BY count(ch) DESC""")
    List<Comment> findByPostIdOrderByChildrenCount(@Param("postId") Long postId);
}
