package com.example.blogex.domain.post.entitiy;

import com.example.blogex.domain.block.entitiy.Block;
import com.example.blogex.domain.comment.entitiy.Comment;
import com.example.blogex.domain.hashtag.entitiy.HashTag;
import com.example.blogex.domain.postlike.entitiy.PostLike;
import com.example.blogex.domain.posttag.entitiy.PostTag;
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
@Getter
@NoArgsConstructor
@Table(name="post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="preview_image")
    private String previewImage;


    @Column(name="summation")
    private String summation;

    @Column(name="title")
    private String title;

    public Post(User user, String previewImage, String summation, String title) {
        this.user = user;
        this.previewImage = previewImage;
        this.summation = summation;
        this.title = title;
    }

    public static Post create(User user, String previewImage, String summation, String title) {
        return new Post(user, previewImage, summation, title);
    }

    public void changePreviewImage(String previewImage) {
        this.previewImage=previewImage;
    }

    public void changeSummation(String summation) {
        this.summation=summation;
    }

    public void changeTitle(String title) {
        this.title=title;
    }

    //Block 의 post 필드 기준으로 매핑된 block 들 저장
    // 이 Post 가 어떤 Block 의 post 로 설정됐을때, 그 block 들이 저장됨
    @OneToMany(mappedBy = "post")
    private List<Block> blocks =new ArrayList<>();

    //Comment 의 post 필드 기준으로 매핑된 comment 들 저장
    // 이 Post 가 어떤 Comment 의 post 로 설정됐을때, 그 comment 들이 저장됨
    @OneToMany(mappedBy = "post")
    private List<Comment> comments =new ArrayList<>();

    //HashTag 의 post 필드 기준으로 매핑된 hashtag 들 저장
    // 이 Post 가 어떤 HashTag 의 post 로 설정됐을때, 그 hashtag 들이 저장됨
    @OneToMany(mappedBy = "post")
    private Set<PostTag> tags=new HashSet<>();

    //PostLike 의 post 필드 기준으로 매핑된 PostLike 들 저장
    // 이 Post 가 어떤 PostLike 의 post 로 설정됐을때, 그 PostLike 들이 저장됨
    @OneToMany(mappedBy = "post")
    private Set<PostLike> likes =new HashSet<>();
}
