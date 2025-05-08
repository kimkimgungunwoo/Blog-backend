package com.example.blogex.domain.postlike.repository;

import com.example.blogex.domain.postlike.entitiy.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike,Long>  {
    //포스트에 달린 좋아요 리스트 불러오기
    //추후에 서비스 계층에서 매핑해서 사용
    List<PostLike> findByPostIdOrderByCreateAtDesc(Long id);

    //유저가 누른 좋아요 리스트 불러오기
    List<PostLike> findByLikedByIdOrderByCreateAtDesc(Long id);
    
    //포스트에 달린 좋아요 개수
    long countByPostId(Long id);
    
    //이미 유저가 좋아요 눌렀는지 확인하는 용도
    boolean existsByPostIdAndLikedById(Long PostId, Long UseId );
    
    //좋아요 삭제 용도
    void deleteByPostIdAndLikedById(Long PostId, Long UseId);
}
