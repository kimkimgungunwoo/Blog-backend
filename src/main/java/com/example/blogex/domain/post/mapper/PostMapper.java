package com.example.blogex.domain.post.mapper;


import com.example.blogex.domain.post.dto.PostCreateRequest;
import com.example.blogex.domain.post.dto.PostCreateResponse;
import com.example.blogex.domain.post.dto.PostInfo;
import com.example.blogex.domain.post.dto.PostUpdateRequest;
import com.example.blogex.domain.post.entitiy.Post;
import com.example.blogex.domain.user.mapper.UserMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
uses = UserMapper.class)
public interface PostMapper {

    @Mapping(target = "previewImageUrl",source="previewImage")
    @Mapping(target="userSimpleInfo",source="user")
    PostInfo toPostInfo(Post post);

    @Mapping(target = "userInfo", source="user")
    PostCreateResponse toResponse(Post post);

}
