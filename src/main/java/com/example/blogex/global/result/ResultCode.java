package com.example.blogex.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    BASED_SUCCESS("C001","기본성공")
    ;


    private final String code;
    private final String message;


}
