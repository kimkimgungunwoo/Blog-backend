package com.example.blogex.domain.block.service;

import com.example.blogex.domain.block.dto.*;
import com.example.blogex.domain.block.entitiy.Block;
import com.example.blogex.domain.block.mapper.BlockMapper;
import com.example.blogex.domain.block.repository.BlockRepository;
import com.example.blogex.domain.post.entitiy.Post;
import com.example.blogex.domain.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class BlockService {
    /*
    필요 기능
    1. 블럭 불러오기
    2.블럭 생성(블럭 추가)
    3.블럭 내용 변경
    4.블럭 순서 변경
     */

    private final BlockRepository blockRepository;
    private final BlockMapper blockMapper;
    private final PostRepository postRepository;

    //포스트에 있는 블럭 모두 불러오기
    public List<BlockInfo> getBlocksByPostId(Long postId) {
        List<Block> blocks= blockRepository.findByPostIdOrderByOrder(postId);
        return blockMapper.toInfos(blocks);
    }


    //블럭 생성
    public BlockCreateResponse createBlock(Long postId, BlockCreateRequest req) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found: " + postId));

        Block block = blockMapper.toEntity(req);

        //현재 포스트의 블럭 개수 파악
        Integer maxOrder = blockRepository.findByPostIdOrderByOrder(postId)
                .stream()
                .map(Block::getOrder)
                .max(Integer::compareTo)
                .orElse(0);

        //블럭 개수 최대값을 order 로
        block.setOrder(maxOrder + 1);
        block.setPost(post);

        Block saved = blockRepository.save(block);

        return BlockCreateResponse.builder()
                .blockInfo(blockMapper.toInfo(saved))
                .createdAt(saved.getCreatedAt())
                .build();
    }

    //블럭 내용 변경(순서변경과 다름)
    public BlockUpdateResponse updateBlock(Long blockId, BlockUpdateRequest req) {
        Block block=blockRepository.findById(blockId).orElseThrow(()
                -> new EntityNotFoundException("Block not found: " + blockId));

        blockMapper.updateEntity(block, req);

        return blockMapper.toUpdateResponse(block);

    }

    //블럭 순서 변경
    public BlockInfo moveBlock(Long blockId, Long postId, int newIndex) {
        Block block = blockRepository.findById(blockId)
                .filter(b -> b.getPost().getId().equals(postId))
                .orElseThrow(() -> new EntityNotFoundException("Block not found: " + blockId));

        List<Block> blocks = blockRepository.findByPostIdOrderByOrder(postId);

        // 기존 블럭 제거
        blocks.removeIf(b -> b.getId().equals(blockId));

        // 새 위치에 삽입
        blocks.add(newIndex, block);

        // 순서 재정렬
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).setOrder(i);
        }

        // 저장
        blockRepository.saveAll(blocks);
        return blockMapper.toInfo(block);
    }






}
