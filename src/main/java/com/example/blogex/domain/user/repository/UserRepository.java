package com.example.blogex.domain.user.repository;


import com.example.blogex.domain.user.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    username 으로 유저 찾기
    유저이름 일부검색
    이메일로 유저찾기


 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByUsernameContaining(String username);
    User findByEmail(String email);

}
