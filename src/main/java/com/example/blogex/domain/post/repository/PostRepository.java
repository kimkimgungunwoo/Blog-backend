package com.example.blogex.domain.post.repository;

import com.example.blogex.domain.post.dto.PostStats;
import com.example.blogex.domain.post.entitiy.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    int countByUserId(Long id);
    //모든 포스트 불러오기, 최신순이 기본
    List<Post> findAllByOrderByCreatedAtDesc();

    //유저 게시글 전부 불러오기
    //최신순이 기본
    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);

    //좋아요 순으로 정렬
    @Query("""
    select p
    from Post p
    left join p.likes l
    group by p
    order by count (l) desc""")
    List<Post> findAllPopularPost();

    //유저 작성글 중 좋아요 순 정렬
    @Query("""
    select p
    from Post p
    left join p.likes l
    where p.user.id =:userId
    group by p
    order by count(l) desc""")
    List<Post> findPopularPostByUserId(@Param("userId") Long userId);

    //댓글 순 정렬
    @Query("""
    select p
    from Post p
    left join p.comments pc
    group by p
    order by count(pc) desc""")
    List<Post> findPopularPostByComments();

    //유저 작성글 중 댓글 순 정렬
    @Query("""
    select p
    from Post p
    left join p.comments pc
    where p.user.id =:userId
    group by p
    order by count(pc) desc""")
    List<Post> findPopularPostByCommentByUserId(@Param("userId") Long userId);

    //블록에 있는 Text 로  게시글 검색
    @Query("""
    select distinct p
    from Post p
    join p.blocks b
    where b.type=com.example.blogex.domain.block.entitiy.BlockType.TEXT
    and b.content like %:kw%
    """)
    List<Post> findPostByContentLike(@Param("kw") String kw);

    //블록에 있는 내용 검색 , 좋아요 순 정렬
    @Query("""
    select distinct p
    from Post p
    join p.blocks b
    left join p.likes l
    where b.type=com.example.blogex.domain.block.entitiy.BlockType.TEXT
    and b.content like %:kw%
    group by p
    order by count(l) desc""")
    List<Post> findPopularPostByContentLike(@Param("kw") String kw);

    //총합 인기글 검색
    @Query("""
    select p
    from Post p
    left join p.likes l
    left join p.comments c
    group by p
    order by (count(distinct l)+count(distinct c)) desc
""")
    List<Post> findByCombinePopularity();

    //댓글 내용으로 검색
    @Query("""
    select p
    from Post p
    join p.comments c
    where c.content like %:kw%
    group by p
    order by p.createdAt desc
""")
    List<Post> findPostByCommentContentLikeOrderByDesc(@Param("kw") String kw);

    //제목으로검색
    List<Post> findByTitleContainingOrderByCreatedAtDesc(String title);
    
    //제목으로검색+좋아요 순 정렬
    @Query("""
    select p
    from Post p
    left join p.likes l
    where p.title like %:kw%
    group by p
    order by count(l) desc
""")
    List<Post> findPopularPostByTitleLike(@Param("kw") String kw);

    //유저이름으로 검색
    @Query("""
    select p
    from Post p
    where p.user.username like %:kw%
    order by p.createdAt desc
""")
    List<Post> findByUsernameContainingOrderByCreateAtDesc(@Param("kw") String kw);
    

    //해쉬태그로검색
    @Query("""
    select p
    from Post p
    join p.tags t
    where t.hashtag.tag like %:kw%
    group by p
    order by p.createdAt desc
""")
    List<Post> findByHashtagContainingOrderByCreateAtDesc(@Param("kw") String kw);


    //태그로 검색+좋아요 순 정렬
    @Query("""
    select p
    from Post p
    left join p.likes l
    join p.tags t
    where t.hashtag.tag like %:kw%
    group by p
    order by count(l) desc
""")
    List<Post> findByTagContainingOrderByLikeDesc(@Param("kw") String kw);

    //유저 게시글 중에서 해쉬태그로 모아보기(velog 기능)
    @Query("""
    select p
    from Post p
    join p.tags t
    where t = :kw and p.user.id =:UserId
    group by p
    order by p.createdAt desc
""")
    List<Post> findByUserIdAndTag(@Param("UserId") Long UserId, @Param("kw") String kw);



    @Query("""
    select new com.example.blogex.domain.post.dto.PostStats(
    p.id,
    count (l),
    count (c)
    )
    from Post p
    left join p.likes l
    left join p.comments c
    
    where p.id IN :ids
    group by p.id
""")
    List<PostStats> findPostStatsByIds(@Param("ids") List<Long> ids);

    @Query("""
    select new com.example.blogex.domain.post.dto.PostStats(
        p.id,
        count(l),
        count(c)
        
    )
    from Post p
    left join p.likes l
    left join p.comments c
    where p.id = :postId
    group by p.id
""")
    PostStats findPostStatsById(@Param("postId") Long postId);


    @Query("""
    select p
    from Post p
    JOIN Follow f On f.followingUser.id=p.user.id
    where f.follower.id = :userId
    ORDER BY p.createdAt desc
""")
    List<Post> getFeed(@Param("userId") Long userId);

}
