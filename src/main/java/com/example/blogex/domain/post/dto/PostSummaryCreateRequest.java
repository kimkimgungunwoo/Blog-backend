package com.example.blogex.domain.post.dto;


import com.example.blogex.domain.block.dto.BlockInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class PostSummaryCreateRequest {
    private List<BlockInfo> contentBlockList;

    @Builder
    public PostSummaryCreateRequest(List<BlockInfo> contentBlockList) {
        this.contentBlockList = contentBlockList;
    }

}
