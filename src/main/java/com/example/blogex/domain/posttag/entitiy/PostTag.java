package com.example.blogex.domain.posttag.entitiy;

import com.example.blogex.domain.hashtag.entitiy.HashTag;
import com.example.blogex.domain.post.entitiy.Post;
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
@Table(name="posttag")
public class PostTag extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="posttag_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name="hashtag_id")
    private HashTag hashtag;

    @Builder
    public PostTag(Post post,
                   HashTag hashtag) {
        this.post = post;
        this.hashtag = hashtag;
    }


}
