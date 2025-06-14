package com.example.blogex.domain.post.dto;


import com.example.blogex.domain.block.dto.BlockInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
public class PostSummaryCreateRequest {
    private List<BlockInfo> contentBlockList;


}
