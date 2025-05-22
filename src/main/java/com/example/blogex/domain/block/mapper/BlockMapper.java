package com.example.blogex.domain.block.mapper;

import com.example.blogex.domain.block.dto.*;
import com.example.blogex.domain.block.entitiy.Block;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlockMapper {

    Block toEntity(BlockCreateRequest blockCreateRequest);

    BlockInfo toInfo(Block block);

    List<BlockInfo> toInfos(List<Block> blocks);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Block block, BlockUpdateRequest blockUpdateRequest);

    BlockUpdateResponse toUpdateResponse(Block block);



}
