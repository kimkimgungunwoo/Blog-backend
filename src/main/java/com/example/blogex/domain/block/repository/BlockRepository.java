package com.example.blogex.domain.block.repository;


import com.example.blogex.domain.block.entitiy.Block;
import com.example.blogex.domain.post.entitiy.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {
    List<Block> findByPostIdOrderByOrder(Long PostId);
}
