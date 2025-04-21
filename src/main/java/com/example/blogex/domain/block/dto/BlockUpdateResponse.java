package com.example.blogex.domain.block.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/*
블록 변경 요청에 대한 반응
변경일자와 content , order 만 반환
 */
@NoArgsConstructor
@Getter
public class BlockUpdateResponse {
    private String content;
    private int order;
    private LocalDateTime updatedAt;

    @Builder
    public BlockUpdateResponse(String content,
                               int order,
                               LocalDateTime updatedAt) {
        this.content = content;
        this.order = order;
        this.updatedAt = updatedAt;
    }


}
