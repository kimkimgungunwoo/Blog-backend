package com.example.blogex.domain.block.dto;


import com.example.blogex.domain.block.entitiy.BlockType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
블록 만들어 내는 DTO, 파라미터로 postId에 연결,
userId는 추후 로그인 세션 기능 만들떄 제거
 */
@Getter
@NoArgsConstructor
public class BlockCreateRequest {
    private String content;
    private BlockType type;
    private int order;
    private Long userId;

    @Builder
    public BlockCreateRequest(String content,
                              BlockType type,
                              int order,
                              Long userId) {
        this.content = content;
        this.type = type;
        this.order = order;
        this.userId = userId;
    }
}
