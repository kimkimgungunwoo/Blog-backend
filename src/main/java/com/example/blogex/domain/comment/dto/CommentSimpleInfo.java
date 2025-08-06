package com.example.blogex.domain.comment.dto;


import com.example.blogex.domain.post.dto.PostInfo;
import com.example.blogex.domain.user.dto.UserSimpleInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class CommentSimpleInfo {
    private Long id;
    private Long postId;
    private String content;
    private UserSimpleInfo userSimpleInfo;


}
