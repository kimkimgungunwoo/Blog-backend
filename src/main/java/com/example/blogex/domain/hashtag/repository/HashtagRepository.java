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

    Collection<HashTag> findAllByTagIn(Collection<String> tags);


}
