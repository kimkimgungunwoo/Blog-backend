package com.example.blogex.domain.hashtag.repository;


import com.example.blogex.domain.hashtag.entitiy.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<HashTag,Long> {

    //태그 이미 존재중인지 확인하는 용도
    boolean existsByTag(String tag);

    //태그로 해쉬태그 찾는 용도
    HashTag findByTag(String tag);
    
    Collection<HashTag> findAllByTagIn(Collection<String> tags);


    //많이 사용된 해쉬태그 목록 보여주기
    @Query("""
    SELECT h
    FROM HashTag h
    left Join h.posts p
    group by h
    order by count(p) DESC""")
    List<HashTag> findPopularTags();

    // 특정 유저가 많이 사용한 해쉬태그 목록 보여주기
    @Query("""
    SELECT h
    from HashTag h
    join h.posts p
    where p.post.user.id=:userId
    group by h
    order by count (p) desc""")
    List<HashTag> findPopularTagsByUserId(Long userId);

}
