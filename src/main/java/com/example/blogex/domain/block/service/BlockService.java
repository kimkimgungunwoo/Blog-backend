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
    필요기능
    1.블럭들 불러오기
    2.블럭 만들기
    3.Post 블럭 추가하기
    4.블럭 수정
    5.블럭 순서 변경
     */

    private final BlockRepository blockRepository;
    private final BlockMapper blockMapper;
    private final PostRepository postRepository;

    public List<BlockInfo> getBlocksByPostId(Long postId) {
        List<Block> blocks= blockRepository.findByPostIdOrderByOrder(postId);
        return blockMapper.toInfos(blocks);
    }



    public BlockCreateResponse createBlock(Long postId, BlockCreateRequest req) {
        // 1) Post 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found: " + postId));

        // 2) DTO -> Entity 변환
        Block block = blockMapper.toEntity(req);

        // 3) 현재 Post 의 최대 Order 확인
        Integer maxOrder = blockRepository.findByPostIdOrderByOrder(postId)
                .stream()
                .map(Block::getOrder)
                .max(Integer::compareTo)
                .orElse(0);

        // 4) 새 블록에 계산된 순서와 Post 설정
        block.setOrder(maxOrder + 1);
        block.setPost(post);

        // 5) 저장
        Block saved = blockRepository.save(block);

        // 6) 응답 DTO 생성
        return BlockCreateResponse.builder()
                .blockInfo(blockMapper.toInfo(saved))
                .createdAt(saved.getCreatedAt())
                .build();
    }

    public BlockUpdateResponse updateBlock(Long blockId, BlockUpdateRequest req) {
        Block block=blockRepository.findById(blockId).orElseThrow(()
                -> new EntityNotFoundException("Block not found: " + blockId));

        blockMapper.updateEntity(block, req);

        return blockMapper.toUpdateResponse(block);

    }

    public BlockInfo moveBlock(Long blockId, Long postId,int newIndex) {
        Block block=blockRepository.findById(blockId)
                .filter(b->b.getPost().getId().equals(postId))
                .orElseThrow(() -> new EntityNotFoundException("Block not found: " + blockId));

        List<Block> blocks= blockRepository.findByPostIdOrderByOrder(postId);
        for(Block b:blocks){
            if(b.getOrder()>=newIndex){
                b.setOrder(b.getOrder()+1);
            }
        }
        block.setOrder(newIndex);

        return blockMapper.toInfo(blockRepository.save(block));
    }


    
    

}
