package com.example.blogex.domain.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
public class UserLoginRequest {
    private String email;
    private String password;
}
