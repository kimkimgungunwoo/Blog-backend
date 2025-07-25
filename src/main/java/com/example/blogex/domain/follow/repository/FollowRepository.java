package com.example.blogex.domain.follow.repository;

import com.example.blogex.domain.follow.entitiy.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    
    //팔로잉목록 불러오기,최신순정렬이 기본
    List<Follow> findByFollowerIdOrderByCreatedAtDesc(Long id);

    //팔로워 목록 불러오기,최신순정렬이 기본값
    List<Follow> findByFollowingUserIdOrderByCreatedAtDesc(Long id);

    boolean existsByFollowerIdAndFollowingUserId(Long followerId, Long followingUserId);

    //내가 얼마나 팔로우하고 있는지 카운트(내가 follower)
    int countByFollowerId(Long id);

    //나를 얼마나 팔로잉하고 있는지 카운트
    int countByFollowingUserId(Long id);

    void deleteByFollowerIdAndFollowingUserId(Long followerId, Long followingUserId);

}
