package com.example.blogex.domain.block.dto;


import com.example.blogex.domain.block.entitiy.BlockType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
블록 만들어 내는 DTO, 파라미터로 postId에 연결,
 */
@Getter
@NoArgsConstructor
public class BlockCreateRequest {
    private String content;
    private BlockType type;

    @Builder
    public BlockCreateRequest(String content,
                              BlockType type
                              ) {
        this.content = content;
        this.type = type;
    }
}
