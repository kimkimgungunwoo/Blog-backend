package com.example.blogex.domain.user.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateResponse {
    private Long id;
    private String username;
    private String password;
    private String email;

    @Builder
    public UserCreateResponse(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
