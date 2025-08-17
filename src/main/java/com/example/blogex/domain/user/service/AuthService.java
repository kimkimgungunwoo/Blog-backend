package com.example.blogex.domain.user.service;

import com.example.blogex.domain.user.dto.UserCreateRequest;
import com.example.blogex.domain.user.dto.UserLoginRequest;
import com.example.blogex.domain.user.entitiy.User;
import com.example.blogex.domain.user.mapper.UserMapper;
import com.example.blogex.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder=new BCryptPasswordEncoder();

    public void signUp(UserCreateRequest userCreateRequest) {
        if(userRepository.findByUsername(userCreateRequest.getUsername())!=null){
            throw new IllegalStateException("dup");
        }

        User user=userMapper.toEntity(userCreateRequest);
        user.setPassword(encoder.encode(userCreateRequest.getPassword()));
        userRepository.save(user);
    }

    public long login(UserLoginRequest userLoginRequest) {
        Optional<User> user= Optional.ofNullable(userRepository.findByEmail(userLoginRequest.getEmail()).orElseThrow(() -> new IllegalStateException("bad password")));
        if(!encoder.matches(userLoginRequest.getPassword(),user.get().getPassword())){
            throw new IllegalStateException("bad password");
        }

        return user.get().getId();

    }



}
