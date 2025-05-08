package com.example.blogex.domain.posttag.repository;

import com.example.blogex.domain.hashtag.entitiy.HashTag;
import com.example.blogex.domain.posttag.entitiy.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long> {
    List<PostTag> findByPostId(Long postId);

    boolean existsByPostIdAndHashtag(Long postId, HashTag hashtag);

    void deleteByPostIdAndHashtag(Long postId, HashTag hashtag);

}
