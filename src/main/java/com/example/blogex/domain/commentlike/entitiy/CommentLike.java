package com.example.blogex.domain.commentlike.entitiy;


import com.example.blogex.domain.comment.entitiy.Comment;
import com.example.blogex.domain.user.entitiy.User;
import com.example.blogex.common.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="commentlike")
public class CommentLike extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User likedBy;

    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment likedComment;

    @Builder
    public CommentLike(User likedBy,
                       Comment likedComment) {
        this.likedBy = likedBy;
        this.likedComment = likedComment;
    }
}
