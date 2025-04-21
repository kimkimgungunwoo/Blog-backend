package com.example.blogex.domain.block.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/*
블럭 내용 변경 요청
content 와 order 에 대한 변경만 가능, type 에 대한 변환은 고려하지 않음
 */
@Getter
@NoArgsConstructor
public class BlockUpdateRequest {

    private String content;
    private int order;

    @Builder
    public BlockUpdateRequest(String content, int order) {
        this.content = content;
        this.order = order;
    }

}
