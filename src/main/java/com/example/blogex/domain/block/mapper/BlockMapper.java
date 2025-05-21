package com.example.blogex.domain.block.mapper;

import com.example.blogex.domain.block.dto.*;
import com.example.blogex.domain.block.entitiy.Block;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BlockMapper {

    Block toEntity(BlockCreateRequest blockCreateRequest);

    BlockInfo toInfo(Block block);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Block block, BlockUpdateRequest blockUpdateRequest);

    BlockUpdateResponse toUpdateResponse(Block block);



}
