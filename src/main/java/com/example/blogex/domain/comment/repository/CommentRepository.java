package com.example.blogex.domain.comment.repository;

import com.example.blogex.domain.comment.dto.CommentStats;
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
    //댓글,답글 구분하지 않고 검색
    List<Comment> findByUserIdOrderByCreatedAtDesc(Long userId);

    //댓글 일부 내용으로 검색
    //답글,댓글 구분하지 않고 검색
    List<Comment> findByContentContainingOrderByCreatedAtDesc(String commentText);

    //포스트에 있는 댓글들 불러오기
    //포스트에 있는 댓글들은 생성시간 오름차순
    //댓글-답글 트리 형태로 반환
    @Query("""
    select distinct c from Comment c
    left join fetch c.replies r
    where c.post.id =:postId
        and c.parentComment is null
    order by c.createdAt asc
""")
    List<Comment> findByPostIdOrderByCreatedAtAsc(Long postId);

    //댓글 좋아요순 정렬
    //쿼리 사용
    @Query("""
    SELECT distinct c
    FROM Comment c
    left join fetch c.replies r
    WHERE c.post.id = :postId
        and c.parentComment is null
    ORDER BY size(c.likes) DESC""")
    List<Comment> findByPostIdOrderByLikes(@Param("postId") Long postId);

    //댓글 답글 순 정렬
    //쿼리사용
    @Query("""
    select distinct c from Comment c
    left join fetch c.replies r
    where c.post.id = :postId
        and c.parentComment is null
    order by size(c.replies) desc
    """)
    List<Comment> findByPostIdOrderByChildrenCount(@Param("postId") Long postId);


    @Query("""
    select new com.example.blogex.domain.comment.dto.CommentStats(
    c.id,
    count (l)
    )
    from Comment c
    left join c.likes l
    where c.id in :ids
    group by c.id
    """)
    List<CommentStats> getCommentStats(@Param("ids") List<Long> ids);
}
