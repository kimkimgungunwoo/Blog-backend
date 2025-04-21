package com.example.blogex.domain.block.entitiy;


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
@Table(name = "block")
public class Block extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    //DB에 type 을 문자열로 저장 , enum 을 더 안전하게 저장하고 활용하는데에 좋음
    @Enumerated(EnumType.STRING)
    @Column(name = "block_type", nullable = false)
    private BlockType type;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "block_order")
    private Integer order;  // 블록 순서

    @Builder
    public Block(Post post, BlockType type, String content, Integer order) {
        this.post = post;
        this.type = type;
        this.content = content;
        this.order = order;
    }
}
