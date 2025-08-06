package com.example.blogex.domain.post.mapper;


import com.example.blogex.domain.post.dto.*;
import com.example.blogex.domain.post.entitiy.Post;
import com.example.blogex.domain.user.mapper.UserMapper;
import org.mapstruct.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface PostMapper {

    @Mapping(target = "previewImageUrl", source = "previewImage")
    @Mapping(target = "userSimpleInfo", source = "user")
    PostInfo toInfo(Post post);

    List<PostInfo> toInfos(List<Post> posts);

    @Mapping(target = "userInfo", source = "user")
    PostCreateResponse toResponse(Post post);

    @Mapping(target = "cntLike",    source = "postStats.cntLike")
    @Mapping(target = "cntComment", source = "postStats.cntComment")
    @Mapping(target="postInfo" ,source="post")
    PostFullInfo toFullInfo(Post post, PostStats postStats);

    default List<PostFullInfo> toFullInfos(
            List<Post> posts,
            List<PostStats> postStatsList
    ) {
        Map<Long, PostStats> statsMap = postStatsList.stream()
                .collect(Collectors.toMap(PostStats::getId, s -> s));
        return posts.stream()
                .map(post -> toFullInfo(post, statsMap.get(post.getId())))
                .toList();
    }
}
