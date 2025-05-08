package com.example.blogex.domain.hashtag.repository;


import com.example.blogex.domain.hashtag.entitiy.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<HashTag,Long> {

    boolean existsByTag(String tag);

    HashTag findByTag(String tag);

    Collection<HashTag> findAllByTagIn(Collection<String> tags);


    @Query("""
    SELECT h
    FROM HashTag h
    left Join h.posts p
    group by h
    order by count(p) DESC""")
    List<HashTag> findPopularTags();

    @Query("""
    SELECT h
    from HashTag h
    join h.posts p
    where p.post.user.id=:userId
    group by h
    order by count (p) desc""")
    List<HashTag> findPopularTagsByUserId(Long userId);

}
