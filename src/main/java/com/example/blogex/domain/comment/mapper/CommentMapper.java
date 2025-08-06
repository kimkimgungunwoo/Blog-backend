package com.example.blogex.domain.comment.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.example.blogex.domain.comment.dto.*;
import com.example.blogex.domain.comment.entitiy.Comment;
import com.example.blogex.domain.post.mapper.PostMapper;
import com.example.blogex.domain.user.mapper.UserMapper;
import org.mapstruct.*;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class,PostMapper.class})
public interface CommentMapper {


    Comment toEntity(CommentCreateRequest request);

    @Mapping(target = "postInfo", source = "post")
    @Mapping(target="userSimpleInfo" , source="user")
    @Mapping(target="replies" , ignore = true)
    CommentInfo toInfo(Comment comment);

    List<CommentInfo> toInfos(List<Comment> comments);

    @Mapping(target = "userSimpleInfo", source = "user")
    ReplyInfo toReplyInfo(Comment comment);

    List<ReplyInfo> toReplyInfos(List<Comment> comments);

    CommentCreateResponse toResponse(Comment comment);

    // 댓글 + 좋아요 통계 단일 매핑
    @Mapping(target = "commentInfo" ,ignore = true)
    CommentFullInfo toFullInfo(Comment comment, CommentStats commentStats);

    @AfterMapping
    default void fillCommentIfo(Comment comment, @MappingTarget CommentFullInfo.CommentFullInfoBuilder target) {
        target.commentInfo(this.toInfo(comment));
    }

    // 댓글 + 좋아요 통계 리스트 매핑
    default List<CommentFullInfo> toFullInfos(
            List<Comment> comments,
            List<CommentStats> commentStatsList
    ) {
        Map<Long, CommentStats> statsMap = commentStatsList.stream()
                .collect(Collectors.toMap(CommentStats::getId, s -> s));
        return comments.stream()
                .map(c -> toFullInfo(c, statsMap.get(c.getId())))
                .toList();
    }

    // 답글 + 좋아요 통계 단일 매핑
    @Mapping(target="replyInfo" ,source="comment")
    ReplyFullInfo toReplyFullInfo(Comment comment, CommentStats commentStats);


    @AfterMapping
    default void fillReplyInfo(Comment comment, @MappingTarget ReplyFullInfo.ReplyFullInfoBuilder target) {
        target.replyInfo(this.toReplyInfo(comment));
    }

    // 답글 + 좋아요 통계 리스트 매핑
    default List<ReplyFullInfo> toReplyFullInfos(
            List<Comment> replies,
            List<CommentStats> commentStatsList
    ) {
        Map<Long, CommentStats> statsMap = commentStatsList.stream()
                .collect(Collectors.toMap(CommentStats::getId, s -> s));
        return replies.stream()
                .map(c -> toReplyFullInfo(c, statsMap.get(c.getId())))
                .toList();
    }

    @Mapping(target = "postId" , source="comment.post.id")
    @Mapping(target="userSimpleInfo", source="user")
    CommentSimpleInfo toSimpleInfo(Comment comment);

    List<CommentSimpleInfo> toSimpleInfos(List<Comment> comments);
}
