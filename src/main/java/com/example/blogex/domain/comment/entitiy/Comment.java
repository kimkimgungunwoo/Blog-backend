package com.example.blogex.domain.comment.entitiy;


import com.example.blogex.domain.commentlike.entitiy.CommentLike;
import com.example.blogex.domain.post.entitiy.Post;
import com.example.blogex.domain.user.entitiy.User;
import com.example.blogex.common.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;

    @Column(name="content")
    private String content;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="parent_comment_id",nullable = true)
    private Comment parentComment;

    @Builder
    public Comment(String content,
                   Post post,
                   User user,
                   Comment parentComment) {
        this.content = content;
        this.post = post;
        this.user = user;
        this.parentComment = parentComment;
    }
    //댓들이 부모 댓글일때 답글들 불러오는 기능
    //parentComment 와 매핑되어 저장->Comment 엔티티안에서 parentComment 기준으로 매핑된 애들 저장
    //이 댓글이 다른 댓글의 parentComment 로 설정되었을 때, 그 자식 댓글들이 저장되는 필드
    @OneToMany(mappedBy = "parentComment",orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();

    //CommentLike 의 likedComment 필드 기준으로 매핑된 commentLike 들 저장
    // 이 Comment 가 어떤 CommentLike 의 likedComment 로 설정됐을때, 그 commentLike 들이 저장됨
    @OneToMany(mappedBy = "likedComment")
    private Set<CommentLike> likes=new HashSet<>();
}
