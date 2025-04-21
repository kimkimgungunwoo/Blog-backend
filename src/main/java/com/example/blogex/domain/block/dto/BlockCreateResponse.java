package com.example.blogex.domain.block.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
유저가 만들어낸 Block 의 정보를 담아 반환

 */
@Getter
@NoArgsConstructor
public class BlockCreateResponse {;
    private BlockInfo blockInfo;
    private LocalDateTime createdAt;

    @Builder
    public BlockCreateResponse(BlockInfo blockInfo, LocalDateTime createdAt) {
        this.blockInfo = blockInfo;
        this.createdAt = createdAt;
    }




}
