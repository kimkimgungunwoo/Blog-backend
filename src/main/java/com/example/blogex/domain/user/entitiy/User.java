package com.example.blogex.domain.user.entitiy;


import com.example.blogex.domain.comment.entitiy.Comment;
import com.example.blogex.domain.commentlike.entitiy.CommentLike;
import com.example.blogex.domain.follow.entitiy.Follow;
import com.example.blogex.domain.post.entitiy.Post;
import com.example.blogex.domain.postlike.entitiy.PostLike;
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
@Table(name="users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="nusername")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="description")
    private String description;

    @Column(name="profileimage")
    private String profileImage;


    @Builder
    public User(String username,
                String email,
                String password,
                String description,
                String profileImage) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.description = description;
        this.profileImage = profileImage;

    }
    //Post 의 user 필드 기준으로 매핑된 post 들 저장
    // 이 User 가 어떤 Post 의 user 로 설정됐을때, 그 post 들이 저장됨
    @OneToMany(mappedBy = "user")
    private List<Post> posts=new ArrayList<>();

    //Comment 의 user 필드 기준으로 매핑된 comment 들 저장
    // 이 User 가 어떤 Comment 의 user 로 설정됐을때, 그 comment 들이 저장됨
    @OneToMany(mappedBy = "user")
    private List<Comment> comments=new ArrayList<>();

    //CommentLike 의 user 필드 기준으로 매핑된 commentLike 들 저장
    // 이 User 가 어떤 CommentLike 의 likedBy 로 설정됐을때, 그 commentLike 들이 저장됨
    @OneToMany(mappedBy = "likedBy")
    private Set<CommentLike> CommentLikes=new HashSet<>();

    //PostLikes 의 user 필드 기준으로 매핑된 postLike 들 저장
    // 이 User 가 어떤 PostLikes 의 likedBy 로 설정됐을때, 그 postLike 들이 저장됨
    @OneToMany(mappedBy = "likedBy")
    private Set<PostLike> PostLikes=new HashSet<>();


    //Follow 의 user 필드 기준으로 매핑된 Follow 들 저장
    // 이 User 가 어떤 Follow 의 follower 로 설정됐을때, 그 Follow 들이 저장됨
    @OneToMany(mappedBy = "follower")
    private List<Follow> followings = new ArrayList<>();

    //Follow 의 user 필드 기준으로 매핑된 Follow 들 저장
    // 이 User 가 어떤 Follow 의 following 로 설정됐을때, 그 Follow 들이 저장됨
    @OneToMany(mappedBy = "followingUser")
    private List<Follow> followers = new ArrayList<>();



}
