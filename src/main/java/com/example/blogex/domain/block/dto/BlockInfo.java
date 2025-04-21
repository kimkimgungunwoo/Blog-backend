package com.example.blogex.domain.block.dto;


import com.example.blogex.domain.block.entitiy.BlockType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
단일 블럭의 정보
post 글 불러올 때 리스트에 담아서 반환
 */
@NoArgsConstructor
@Getter
public class BlockInfo {
    private Long id;
    private BlockType type;
    private String content;
    private int order;

    @Builder
    public BlockInfo(
                    Long id,
                    BlockType type,
                    String content,
                    int order) {
        this.type = type;
        this.content = content;
        this.order = order;
        this.id = id;
    }

}
