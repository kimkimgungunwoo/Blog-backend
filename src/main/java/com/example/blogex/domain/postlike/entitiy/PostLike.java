package com.example.blogex.domain.postlike.entitiy;

import com.example.blogex.domain.post.entitiy.Post;
import com.example.blogex.domain.user.entitiy.User;
import com.example.blogex.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="postlike")
public class PostLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User likedBy;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @Builder
    public PostLike(User likedBy, Post post) {
        this.likedBy = likedBy;
        this.post = post;
    }
}
