package com.example.blogex.domain.post.dto;


import com.example.blogex.domain.block.dto.BlockInfo;
import com.example.blogex.domain.comment.dto.CommentFullInfo;
import com.example.blogex.domain.comment.dto.CommentInfo;
import com.example.blogex.domain.hashtag.dto.HashtagDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/*
포스트 내용 전체보기
포스트의 미리보기 내용들과 연결된 블럭리스트 작성된 댓글리스트 읽어오기
 */
@NoArgsConstructor
@Getter
public class PostContent {

    private List<BlockInfo> blockList;
    private List<CommentFullInfo> commentList;
    private PostFullInfo postInfo;
    private List<HashtagDto> hashtagList;

    @Builder
    public PostContent(List<BlockInfo> blockList, List<CommentFullInfo> commentList, PostFullInfo postInfo,
                       List<HashtagDto> hashtagList) {
        this.blockList = blockList;
        this.commentList = commentList;
        this.postInfo = postInfo;
        this.hashtagList = hashtagList;
    }

}
