package com.example.blogex.domain.post.mapper;


import com.example.blogex.domain.post.dto.PostCreateRequest;
import com.example.blogex.domain.post.dto.PostCreateResponse;
import com.example.blogex.domain.post.dto.PostInfo;
import com.example.blogex.domain.post.dto.PostUpdateRequest;
import com.example.blogex.domain.post.entitiy.Post;
import com.example.blogex.domain.user.mapper.UserMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
uses = UserMapper.class)
public interface PostMapper {

    @Mapping(target = "previewImageUrl",source="previewImage")
    @Mapping(target="userSimpleInfo",source="user")
    PostInfo toInfo(Post post);

    List<PostInfo> toInfos(List<Post> posts);

    @Mapping(target = "userInfo", source="user")
    PostCreateResponse toResponse(Post post);

}
