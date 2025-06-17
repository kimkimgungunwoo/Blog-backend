package com.example.blogex.domain.posttag.service;

import com.example.blogex.domain.hashtag.entitiy.HashTag;
import com.example.blogex.domain.hashtag.repository.HashtagRepository;
import com.example.blogex.domain.post.entitiy.Post;
import com.example.blogex.domain.post.repository.PostRepository;
import com.example.blogex.domain.posttag.entitiy.PostTag;
import com.example.blogex.domain.posttag.repository.PostTagRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PostTagService {
    private final PostTagRepository postTagRepository;
    private final HashtagRepository hashtagRepository;
    private final PostRepository postRepository;

    public PostTag addTag(Long postId, String tagValue) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        HashTag tag = hashtagRepository.findByTag(tagValue);
        if (tag == null) {
            tag = hashtagRepository.save(HashTag.builder().tag(tagValue).build());
        }

        // 중복 방지
        if (postTagRepository.existsByPostIdAndHashtag(postId, tag)) {
            throw new IllegalStateException("Tag already exists for this post");
        }

        PostTag postTag = new PostTag(post, tag);
        post.getTags().add(postTag);
        tag.getPosts().add(postTag);

        return postTagRepository.save(postTag);
    }

    public void deleteTag(Long postId, String tagValue) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        HashTag tag = hashtagRepository.findByTag(tagValue);
        if (tag == null) return; // 태그 자체가 없으면 아무 작업 안 함

        // 연결 제거 (양방향 관계)
        post.getTags().removeIf(pt -> pt.getHashtag().equals(tag));
        tag.getPosts().removeIf(pt -> pt.getPost().equals(post));

        postTagRepository.deleteByPostIdAndHashtag(postId, tag);
    }

    public int getHashtagUsageCountByUser(Long userId, String tagValue) {
        HashTag tag = hashtagRepository.findByTag(tagValue);
        if (tag == null) return 0;

        return postTagRepository.countByUserAndHashtag(userId, tag);
    }
}