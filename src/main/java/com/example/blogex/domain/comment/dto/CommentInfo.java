package com.example.blogex.domain.comment.dto;


import com.example.blogex.domain.post.dto.PostInfo;
import com.example.blogex.domain.user.dto.UserSimpleInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Setter
public class CommentInfo {
    private Long id;
    // 댓글목록에서도 게시글 확인할 수 있도록
    private PostInfo postInfo;
    private UserSimpleInfo userSimpleInfo;
    private String content;
    private LocalDateTime createdAt;
    private List<ReplyFullInfo> replies;

    @Builder
    public CommentInfo(
            Long id,
            PostInfo postInfo,
            String content,
            LocalDateTime createdAt,
            List<ReplyFullInfo> replies,
            UserSimpleInfo userSimpleInfo

    ){
        this.id = id;
        this.postInfo = postInfo;
        this.userSimpleInfo = userSimpleInfo;
        this.content = content;
        this.createdAt = createdAt;
        this.replies = replies;
    }


}
