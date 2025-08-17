package com.example.blogex.domain.user.repository;


import com.example.blogex.domain.user.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByUsernameContaining(String username);
    Optional<User> findByEmail(String email);

    @Query("""
    select u
    from User u
    left join u.followers f
    group by u
    order by count(f) desc""")
    List<User> findPopularByFollower();

    @Query("""
    select u
    from User u
    left join u.comments c
    left join u.posts p
    group by u
    order by (count(distinct c)+count(distinct p)) desc""")
    List<User> findAllByOrderByActivityDesc();



}
