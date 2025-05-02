package com.example.blogex.domain.hashtag.entitiy;

import com.example.blogex.domain.post.entitiy.Post;
import com.example.blogex.common.Entity.BaseEntity;
import com.example.blogex.domain.posttag.entitiy.PostTag;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="hashtag")

public class HashTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hashtag_id")
    private Long id;

    @Column(name="tag",unique = true)
    private String tag;

    @Builder
    public HashTag(String tag) {
        this.tag = tag;
    }

    @OneToMany(mappedBy = "hashtag")
    private Set<PostTag> posts=new HashSet<>();



}
