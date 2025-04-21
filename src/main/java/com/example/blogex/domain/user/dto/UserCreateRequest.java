package com.example.blogex.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UserCreateRequest {

    private String username;
    private String password;
    private String email;

    @Builder
    public UserCreateRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
