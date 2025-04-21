package com.example.blogex.domain.follow.entitiy;

import com.example.blogex.domain.user.entitiy.User;
import com.example.blogex.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="follow")
public class Follow extends BaseEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="follow_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name="follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private User followingUser;

    @Builder
    public Follow(User follower,
                  User followingUser) {
        this.follower = follower;
        this.followingUser = followingUser;
    }
}

