package com.example.blogex.domain.comment.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.example.blogex.domain.comment.dto.CommentCreateRequest;
import com.example.blogex.domain.comment.dto.CommentCreateResponse;
import com.example.blogex.domain.comment.dto.CommentInfo;
import com.example.blogex.domain.comment.dto.ReplyInfo;
import com.example.blogex.domain.comment.entitiy.Comment;
import com.example.blogex.domain.post.mapper.PostMapper;
import com.example.blogex.domain.user.mapper.UserMapper;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.awt.*;
import java.util.List;

@Mapper(componentModel="spring"
,uses = {UserMapper.class, PostMapper.class})
public interface CommentMapper {

    @Mapping(target = "postInfo",source = "post")
    @Mapping(target="userSimpleInfo",source="user")
    CommentInfo toInfo(Comment comment);

    List<CommentInfo> toInfos(List<Comment> comments);

    @Mapping(target="userSimpleInfo",source="user")
    ReplyInfo toReplyInfo(Comment comment);

    List<ReplyInfo> toReplyInfos(List<Comment> comments);



    CommentCreateResponse toResponse(Comment comment);




}
